package primaryWeb

import core.usecase.AddLogUseCase
import org.koin.core.module.Module
import org.koin.dsl.module
import primaryWeb.usecase.AddLogUseCaseImpl

public val primaryWebModule: Module = module {
  single<AddLogUseCase> {
    AddLogUseCaseImpl(get(), get())
  }
}
