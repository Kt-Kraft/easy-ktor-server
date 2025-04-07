package core.port

import core.models.BasicCredential
import core.models.DatabaseConfig
import core.models.DeploymentConfig

public interface DeploymentEnvPort {
  public val deploymentEnv: String
}

public interface DeploymentConfigPort {
  public val deploymentConfig: DeploymentConfig
}

public interface DatabaseConfigPort {
  public val databaseConfig: DatabaseConfig
}

public interface BasicCredentialPort {
  public val basicCredential: BasicCredential
}
