package core.di

import core.internal.DeploymentConfigInternal
import core.internal.DeploymentEnvInternal
import core.internal.GetBasicCredentialInternal
import core.internal.GetDatabaseConfigInternal
import core.usecase.GetBasicCredentialUseCase
import core.usecase.GetDatabaseConfigUseCase
import core.usecase.GetDeploymentConfigUseCase
import core.usecase.GetDeploymentEnvUseCase
import org.koin.dsl.module

internal val envInternalModule = module {
  single<GetDeploymentEnvUseCase> {
    DeploymentEnvInternal(get())
  }
  single<GetDeploymentConfigUseCase> {
    DeploymentConfigInternal(get())
  }
  single<GetDatabaseConfigUseCase> {
    GetDatabaseConfigInternal(get())
  }
  single<GetBasicCredentialUseCase> {
    GetBasicCredentialInternal(get())
  }
}
