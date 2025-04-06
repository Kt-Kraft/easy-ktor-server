package env

import core.port.BasicCredentialPort
import core.port.DatabaseConfigPort
import core.port.DeploymentConfigPort
import core.port.DeploymentEnvPort
import core.usecase.GetBasicCredentialUseCase
import core.usecase.GetDatabaseConfigUseCase
import core.usecase.GetDeploymentConfigUseCase
import core.usecase.GetDeploymentEnvUseCase
import env.adapter.DeploymentEnvAdapter
import env.adapter.HoconBasedConfigAdapter
import env.impl.GetBasicCredentialUseCaseImpl
import env.impl.GetDatabaseConfigUseCaseImpl
import env.impl.GetDeploymentConfigUseCaseImpl
import env.impl.GetDeploymentEnvUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

public val envModule: Module = module {
  single<DeploymentEnvPort> {
    DeploymentEnvAdapter()
  }

  single<GetDeploymentEnvUseCase> {
    GetDeploymentEnvUseCaseImpl(get())
  }

  single {
    val getDeploymentEnv = get<GetDeploymentEnvUseCase>()
    HoconBasedConfigAdapter(getDeploymentEnv())
  } binds arrayOf(
    DeploymentConfigPort::class,
    DatabaseConfigPort::class,
    BasicCredentialPort::class,
  )

  single<GetDeploymentConfigUseCase> {
    GetDeploymentConfigUseCaseImpl(get())
  }

  single<GetDatabaseConfigUseCase> {
    GetDatabaseConfigUseCaseImpl(get())
  }

  single<GetBasicCredentialUseCase> {
    GetBasicCredentialUseCaseImpl(get())
  }
}
