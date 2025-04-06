package env.impl

import core.models.BasicCredential
import core.models.DeploymentConfig
import core.port.BasicCredentialPort
import core.port.DatabaseConfigPort
import core.port.DeploymentConfigPort
import core.port.DeploymentEnvPort
import core.usecase.GetBasicCredentialUseCase
import core.usecase.GetDatabaseConfigUseCase
import core.usecase.GetDeploymentConfigUseCase
import core.usecase.GetDeploymentEnvUseCase
import java.util.Properties

internal class GetDeploymentEnvUseCaseImpl(
  private val deploymentEnvPort: DeploymentEnvPort,
) : GetDeploymentEnvUseCase {

  override fun invoke(): String {
    return deploymentEnvPort.deploymentEnv
  }
}

internal class GetDeploymentConfigUseCaseImpl(
  private val deploymentConfigPort: DeploymentConfigPort,
) : GetDeploymentConfigUseCase {

  override fun invoke(): DeploymentConfig {
    return deploymentConfigPort.deploymentConfig
  }
}

internal class GetDatabaseConfigUseCaseImpl(
  private val databaseConfigPort: DatabaseConfigPort,
) : GetDatabaseConfigUseCase {

  override fun invoke(): Properties {
    return databaseConfigPort.databaseConfig
  }
}

internal class GetBasicCredentialUseCaseImpl(
  private val basicCredentialPort: BasicCredentialPort,
) : GetBasicCredentialUseCase {

  override fun invoke(): BasicCredential {
    return basicCredentialPort.basicCredential
  }
}
