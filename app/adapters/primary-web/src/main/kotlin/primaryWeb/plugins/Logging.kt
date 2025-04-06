package primaryWeb.plugins

import core.usecase.GetDeploymentEnvUseCase
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.calllogging.CallLogging
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

internal fun Application.configureLogging() {
  val getDeploymentEnv by inject<GetDeploymentEnvUseCase>()
  val deploymentEnv = getDeploymentEnv()

  install(CallLogging) {
    level = when (deploymentEnv) {
      "prod" -> Level.INFO
      "local" -> Level.DEBUG
      else -> Level.INFO
    }
  }
}
