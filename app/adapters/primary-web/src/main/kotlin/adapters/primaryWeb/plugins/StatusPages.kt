package adapters.primaryWeb.plugins

import adapters.primaryWeb.models.response.toErrorResponse
import com.auth0.jwt.exceptions.JWTVerificationException
import core.errors.DomainException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import java.sql.SQLException

internal fun Application.configureStatusPage() {
  install(StatusPages) {
    status(HttpStatusCode.Unauthorized) { call, status ->
      call.respondWithError(
        HttpStatusCode.Unauthorized,
        "Unauthorized",
        "Invalid token",
        listOf()
      )
    }

    exception<Throwable> { call, cause ->
      when (cause) {
        is RequestValidationException -> call.respondWithError(
          HttpStatusCode.BadRequest,
          "Request Validation",
          cause.localizedMessage.orEmpty(),
          cause.reasons
        )

        is JWTVerificationException -> call.respondWithError(
          HttpStatusCode.Unauthorized,
          "Unauthorized",
          "Invalid token",
          listOf()
        )

        is SecurityException -> call.respondWithError(
          HttpStatusCode.Forbidden,
          "Security",
          "Access denied due to security reasons.",
          listOf()
        )

        is SQLException -> call.respondWithError(
          HttpStatusCode.Forbidden,
          "SQL",
          "Database error",
          listOf()
        )

        is IllegalArgumentException -> call.respondWithError(
          HttpStatusCode.BadRequest,
          "Illegal Argument",
          cause.localizedMessage.orEmpty(),
          listOf()
        )

        else -> {
          call.respond(
            status = HttpStatusCode.InternalServerError,
            message = "An unexpected error occurred. Please try again later."
          )
          throw cause
        }
      }
    }
  }
}

private suspend fun ApplicationCall.respondWithError(
  status: HttpStatusCode,
  title: String,
  detail: String,
  specifics: List<String>
) {
  respond(
    status = status,
    message = DomainException(
      title = title,
      detail = detail,
      specifics = specifics,
    ).toErrorResponse()
  )
}
