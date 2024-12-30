package core.di

import org.koin.core.module.Module
import org.koin.dsl.module

public val coreModule: Module = module {
  includes(
    envInternalModule,
    logInternalModule
  )
}
