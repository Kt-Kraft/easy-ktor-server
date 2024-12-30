package adapters.primaryWeb.plugins

import adapters.primaryWeb.routes.generalRouting
import adapters.primaryWeb.routes.v1
import io.ktor.server.application.Application
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

internal fun Application.configureRouting() {
  routing {
    route("/api") {
      generalRouting()
      v1()
    }
  }
}
