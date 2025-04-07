package core.models

public data class DeploymentConfig(
  val env: String,
  val version: String,
  val buildNumber: String,
)

public data class DataSourceConfig(
  val url: String,
  val user: String,
  val password: String
)

public data class DatabaseConfig(
  val dataSourceClassName: String,
  val dataSource: DataSourceConfig,
  val autoCommit: Boolean
)

public data class BasicCredential(
  val username: String,
  val password: String,
)
