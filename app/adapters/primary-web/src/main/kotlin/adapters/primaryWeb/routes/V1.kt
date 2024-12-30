package adapters.primaryWeb.routes

import adapters.primaryWeb.routes.v1.logRouting
import io.ktor.server.routing.Route
import io.ktor.server.routing.route

internal fun Route.v1() {
  route("/v1") {
    logRouting()
  }
}
