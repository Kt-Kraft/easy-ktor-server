package adapters.persist.di

import adapters.persist.LogAdapter
import adapters.persist.PersistAdapter
import adapters.persist.errors.DatabaseErrorInspector
import adapters.persist.errors.PgErrorInspector
import adapters.persist.repository.LogRepository
import core.port.AddLogPort
import core.port.BootPersistStoragePort
import core.port.PersistTransactionPort
import core.port.ShutdownPersistStoragePort
import core.usecase.GetDatabaseConfigUseCase
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

public val persistModule: Module = module {
  single<DatabaseErrorInspector> {
    PgErrorInspector()
  }

  single {
    PersistAdapter(get<GetDatabaseConfigUseCase>()(), get())
  } binds arrayOf(
    BootPersistStoragePort::class,
    ShutdownPersistStoragePort::class,
    PersistTransactionPort::class,
  )

  single {
    LogAdapter(LogRepository())
  } binds arrayOf(
    AddLogPort::class
  )
}
