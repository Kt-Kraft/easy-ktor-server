package adapters.primaryWeb.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation

internal fun Application.configureRequestValidation() {
  install(RequestValidation) { }
}
