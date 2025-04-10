package persist.adapter

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import core.models.DatabaseConfig
import core.port.BootPersistStoragePort
import core.port.PersistTransactionPort
import core.port.RequiresTransactionContext
import core.port.ShutdownPersistStoragePort
import javax.sql.DataSource
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.withSuspendTransaction
import org.jetbrains.exposed.sql.transactions.transactionManager
import persist.errors.DatabaseErrorInspector
import persist.mapper.throwAsDomainException

internal class PersistAdapter(
  private val databaseConfig: DatabaseConfig,
  private val errorInspector: DatabaseErrorInspector,
) : BootPersistStoragePort, ShutdownPersistStoragePort, PersistTransactionPort {

  private lateinit var ds: HikariDataSource
  private lateinit var db: Database

  override suspend fun <T> withNewTransaction(block: suspend () -> T): T {
    return try {
      newSuspendedTransaction(Dispatchers.IO, db = db) {
        block()
      }
    } catch (e: ExposedSQLException) {
      e.throwAsDomainException(errorInspector)
    }
  }

  @RequiresTransactionContext
  override suspend fun <T> withExistingTransaction(block: suspend () -> T): T {
    val tx = db.transactionManager.currentOrNull()
      ?: error("withExistingTransaction(): no current transaction in context")
    check(tx.connection.isClosed) { "withExistingTransaction(): current transaction is closed" }

    return try {
      tx.withSuspendTransaction(Dispatchers.IO) {
        block()
      }
    } catch (e: ExposedSQLException) {
      e.throwAsDomainException(errorInspector)
    }
  }

  @RequiresTransactionContext
  override suspend fun <T> withTransaction(block: suspend () -> T): T {
    val tx = TransactionManager.currentOrNull()
    return try {
      if (tx == null || tx.connection.isClosed) {
        newSuspendedTransaction(Dispatchers.IO, db = db) {
          block()
        }
      } else {
        tx.withSuspendTransaction(Dispatchers.IO) {
          block()
        }
      }
    } catch (e: ExposedSQLException) {
      e.throwAsDomainException(errorInspector)
    }
  }

  override suspend fun <T> bootStorage(preInit: suspend () -> T) {
    ds = HikariDataSource(createHikariConfig(databaseConfig))
    db = Database.connect(ds)
    withNewTransaction {
      preInit.invoke()
      runFlyway(ds)
    }
  }

  override fun shutdownStorage() {
    if (this::ds.isInitialized) {
      ds.close()
    }
  }

  private fun runFlyway(datasource: DataSource) {
    val flyway = Flyway.configure()
      .dataSource(datasource)
      .load()
    flyway.info()
    flyway.migrate()
  }

  private fun createHikariConfig(config: DatabaseConfig): HikariConfig {
    return HikariConfig().apply {
      dataSourceClassName = config.dataSourceClassName
      addDataSourceProperty("url", config.dataSource.url)
      addDataSourceProperty("user", config.dataSource.user)
      addDataSourceProperty("password", config.dataSource.password)
      isAutoCommit = config.autoCommit
    }
  }
}
