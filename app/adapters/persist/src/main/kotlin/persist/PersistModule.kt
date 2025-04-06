package persist

import persist.errors.DatabaseErrorInspector
import persist.errors.PgErrorInspector
import core.port.AddLogPort
import core.port.BootPersistStoragePort
import core.port.PersistTransactionPort
import core.port.ShutdownPersistStoragePort
import core.usecase.AddLogUseCase
import core.usecase.GetDatabaseConfigUseCase
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module
import persist.adapter.LogAdapter
import persist.adapter.PersistAdapter
import persist.impl.AddLogUseCaseImpl

public val persistModule: Module = module {
  single<DatabaseErrorInspector> {
    PgErrorInspector()
  }

  single {
    val getDatabaseConfig = get<GetDatabaseConfigUseCase>()
    PersistAdapter(getDatabaseConfig(), get())
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

  single<AddLogUseCase> {
    AddLogUseCaseImpl(get(), get())
  }
}
