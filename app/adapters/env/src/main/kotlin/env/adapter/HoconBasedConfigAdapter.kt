package env.adapter

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import core.models.BasicCredential
import core.models.DataSourceConfig
import core.models.DatabaseConfig
import core.models.DeploymentConfig
import core.port.BasicCredentialPort
import core.port.DatabaseConfigPort
import core.port.DeploymentConfigPort

internal class HoconBasedConfigAdapter(
  deploymentEnv: String,
) : DeploymentConfigPort, DatabaseConfigPort, BasicCredentialPort {

  private val config: Config

  init {
    val envConfig = ConfigFactory.load("config-$deploymentEnv.conf")
    val commonConf = ConfigFactory.load("config-common.conf")
    val rootConfig = ConfigFactory.load().withFallback(envConfig).withFallback(commonConf).resolve()
    this.config = rootConfig.getConfig("app-config")
  }

  override val deploymentConfig: DeploymentConfig by lazy {
    val node = config.getConfig("deployment")
    DeploymentConfig(
      node.getString("env"),
      node.getString("version"),
      node.getString("buildNumber"),
    )
  }

  override val databaseConfig: DatabaseConfig by lazy {
    val node = config.getConfig("main-db.hikari")
    DatabaseConfig(
      dataSourceClassName = node.getString("dataSourceClassName"),
      dataSource = DataSourceConfig(
        url = node.getString("dataSource.url"),
        user = node.getString("dataSource.user"),
        password = node.getString("dataSource.password")
      ),
      autoCommit = node.getBoolean("autoCommit")
    )
  }

  override val basicCredential: BasicCredential by lazy {
    val node = config.getConfig("auth.basic")
    BasicCredential(
      node.getString("username"),
      node.getString("password"),
    )
  }
}
