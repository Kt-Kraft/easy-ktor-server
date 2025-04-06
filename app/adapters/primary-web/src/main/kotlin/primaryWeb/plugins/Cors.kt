package primaryWeb.plugins

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cors.routing.CORS

internal fun Application.configureCors() {
  install(CORS) {
    anyHost()

    allowCredentials = true
    allowNonSimpleContentTypes = true

    // Allow only necessary methods
    allowMethod(HttpMethod.Get)
    allowMethod(HttpMethod.Post)

    // Allow only necessary headers
    allowHeader(HttpHeaders.ContentType)
    allowHeader(HttpHeaders.Authorization)
  }
}
