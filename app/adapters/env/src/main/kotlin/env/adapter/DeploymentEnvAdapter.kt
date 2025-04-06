package env.adapter

import core.port.DeploymentEnvPort

internal class DeploymentEnvAdapter : DeploymentEnvPort {

  override val deploymentEnv: String by lazy {
    System.getenv(DEPLOYMENT_ENV)
      ?: error("<$DEPLOYMENT_ENV> environment variable is missing")
  }

  companion object {
    const val DEPLOYMENT_ENV = "APP_DEPLOYMENT_ENV"
  }
}
