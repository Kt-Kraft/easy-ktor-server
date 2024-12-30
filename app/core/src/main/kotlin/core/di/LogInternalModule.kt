package core.di

import core.internal.AddLogInternal
import core.usecase.AddLogUseCase
import org.koin.dsl.module

internal val logInternalModule = module {
  single<AddLogUseCase> {
    AddLogInternal(get(), get())
  }
}
