package core.models

public data class DeploymentConfig(
  val env: String,
  val version: String,
  val buildNumber: String,
)
