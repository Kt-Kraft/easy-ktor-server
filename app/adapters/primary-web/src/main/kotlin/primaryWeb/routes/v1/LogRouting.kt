package primaryWeb.routes.v1

import core.usecase.AddLogUseCase
import io.bkbn.kompendium.core.metadata.PostInfo
import io.bkbn.kompendium.core.plugin.NotarizedRoute
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import primaryWeb.models.request.LogRequest
import primaryWeb.models.request.toLog
import primaryWeb.models.response.ErrorResponse

internal fun Route.logRouting() {
  val addLog by inject<AddLogUseCase>()

  authenticate("auth-basic") {
    route("/logs/") {
      logDocumentation()
      post {
        val request = call.receive<LogRequest>()
        addLog(request.toLog()).fold(
          { call.respond(HttpStatusCode.OK, "Log successfully added") },
          { call.respond(HttpStatusCode.InternalServerError, it.localizedMessage) }
        )
      }
    }
  }
}

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
