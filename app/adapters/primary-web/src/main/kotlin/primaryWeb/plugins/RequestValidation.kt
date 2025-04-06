package primaryWeb.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import primaryWeb.models.request.LogRequest

internal fun Application.configureRequestValidation() {
  install(RequestValidation) {
    validate<LogRequest> { req ->
      if (req.type.isEmpty() || req.details.isEmpty()) {
        ValidationResult.Invalid("Invalid request body")
      } else {
        ValidationResult.Valid
      }
    }
  }
}
