package primaryWeb.routes

import io.bkbn.kompendium.core.routes.swagger
import io.ktor.server.routing.Route

internal fun Route.generalRouting() {
  swagger(path = "/docs/", swaggerVersion = "3.23.4")
}
