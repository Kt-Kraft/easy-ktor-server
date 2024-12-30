package core.usecase

import core.models.BasicCredential
import core.models.DeploymentConfig
import java.util.Properties

public fun interface GetDeploymentEnvUseCase {
  public operator fun invoke(): String
}

public fun interface GetDeploymentConfigUseCase {
  public operator fun invoke(): DeploymentConfig
}

public fun interface GetDatabaseConfigUseCase {
  public operator fun invoke(): Properties
}

public fun interface GetBasicCredentialUseCase {
  public operator fun invoke(): BasicCredential
}
