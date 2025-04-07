package env

import core.port.BasicCredentialPort
import core.port.DatabaseConfigPort
import core.port.DeploymentConfigPort
import core.port.DeploymentEnvPort
import env.adapter.DeploymentEnvAdapter
import env.adapter.HoconBasedConfigAdapter
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

public val envModule: Module = module {
  single<DeploymentEnvPort> {
    DeploymentEnvAdapter()
  }

  single {
    val deploymentEnv = get<DeploymentEnvPort>().deploymentEnv
    HoconBasedConfigAdapter(deploymentEnv)
  } binds arrayOf(
    DeploymentConfigPort::class,
    DatabaseConfigPort::class,
    BasicCredentialPort::class,
  )
}
