package adapters.primaryWeb.routes.v1

import adapters.primaryWeb.ext.logDebug
import adapters.primaryWeb.models.request.LogRequest
import adapters.primaryWeb.models.response.ErrorResponse
import core.models.Log
import core.usecase.AddLogUseCase
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

internal fun Route.logRouting() {
  val addLog by inject<AddLogUseCase>()

  authenticate("auth-basic") {
    route("/logs/") {
      logDocumentation()
      post {
        val userId = call.principal<UserIdPrincipal>()?.name
        if (userId == null) {
          call.logDebug("Logs:", "Unauthorized access attempt to /logs")
          call.respond(HttpStatusCode.Unauthorized, "Unauthorized access")
          return@post
        }
        val (type, details) = call.receive<LogRequest>()
        addLog(Log(type = type, details = details))
        call.respond(HttpStatusCode.OK, "Log successfully added")
      }
    }
  }
}

// -------------------------
// Documentation
// -------------------------

private fun Route.logDocumentation() {
  install(NotarizedRoute()) {
    tags = setOf("Logs")
    post = PostInfo.builder {
      summary("Add log")
      description("Add user logs")
      request {
        requestType<LogRequest>()
        description("Data required to add new log")
      }
      response {
        responseCode(HttpStatusCode.OK)
        responseType<Unit>()
        description("Will return success message")
      }
      canRespond {
        responseCode(HttpStatusCode.Unauthorized)
        responseType<ErrorResponse>()
        description("Could not handle the request")
      }
    }
  }
}
