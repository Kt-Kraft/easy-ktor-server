package adapters.env.di

import adapters.env.DeploymentEnvAdapter
import adapters.env.HoconBasedConfigAdapter
import core.port.BasicCredentialPort
import core.port.DatabaseConfigPort
import core.port.DeploymentConfigPort
import core.port.DeploymentEnvPort
import core.usecase.GetDeploymentEnvUseCase
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

public val envModule: Module = module {
  single<DeploymentEnvPort> {
    DeploymentEnvAdapter()
  }

  single {
    HoconBasedConfigAdapter(get<GetDeploymentEnvUseCase>()())
  } binds arrayOf(
    DeploymentConfigPort::class,
    DatabaseConfigPort::class,
    BasicCredentialPort::class,
  )
}
