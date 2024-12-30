package core.port

import core.models.BasicCredential
import core.models.DeploymentConfig
import java.util.Properties

public interface DeploymentEnvPort {
  public val deploymentEnv: String
}

public interface DeploymentConfigPort {
  public val deploymentConfig: DeploymentConfig
}

public interface DatabaseConfigPort {
  public val databaseConfig: Properties
}

public interface BasicCredentialPort {
  public val basicCredential: BasicCredential
}
