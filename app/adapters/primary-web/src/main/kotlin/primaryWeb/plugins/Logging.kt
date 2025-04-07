package primaryWeb.plugins

import core.port.DeploymentEnvPort
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.calllogging.CallLogging
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

internal fun Application.configureLogging() {
  val getDeploymentEnvPort by inject<DeploymentEnvPort>()
  val deploymentEnv = getDeploymentEnvPort.deploymentEnv

  install(CallLogging) {
    level = when (deploymentEnv) {
      "prod" -> Level.INFO
      "local" -> Level.DEBUG
      else -> Level.INFO
    }
  }
}
