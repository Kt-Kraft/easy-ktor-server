package persist

import core.port.AddLogPort
import core.port.BootPersistStoragePort
import core.port.DatabaseConfigPort
import core.port.PersistTransactionPort
import core.port.ShutdownPersistStoragePort
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module
import persist.adapter.LogAdapter
import persist.adapter.PersistAdapter
import persist.errors.DatabaseErrorInspector
import persist.errors.PgErrorInspector

public val persistModule: Module = module {
  single<DatabaseErrorInspector> {
    PgErrorInspector()
  }

  single {
    val databaseConfig = get<DatabaseConfigPort>().databaseConfig
    PersistAdapter(databaseConfig, get())
  } binds arrayOf(
    BootPersistStoragePort::class,
    ShutdownPersistStoragePort::class,
    PersistTransactionPort::class,
  )

  single {
    LogAdapter()
  } binds arrayOf(
    AddLogPort::class
  )
}
