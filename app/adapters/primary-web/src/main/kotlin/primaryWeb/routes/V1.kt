package primaryWeb.routes

import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import primaryWeb.routes.v1.logRouting

internal fun Route.v1() {
  route("/v1") {
    logRouting()
  }
}
