package primaryWeb.plugins

import io.bkbn.kompendium.core.plugin.NotarizedApplication
import io.bkbn.kompendium.json.schema.KotlinXSchemaConfigurator
import io.bkbn.kompendium.oas.OpenApiSpec
import io.bkbn.kompendium.oas.component.Components
import io.bkbn.kompendium.oas.info.Info
import io.bkbn.kompendium.oas.security.BasicAuth
import io.bkbn.kompendium.oas.server.Server
import io.ktor.server.application.Application
import io.ktor.server.application.install
import java.net.URI

internal fun Application.configureDocumentation() {
  install(NotarizedApplication()) {
    spec = {
      OpenApiSpec(
        openapi = "3.0.0",
        info = Info(
          title = "Ktor Server",
          version = "0.1.0",
          description = "Ktor API Sample",
        ),
        servers = mutableListOf(
          Server(
            url = URI("https://sample.com"),
            description = "Production Server",
          ),
          Server(
            url = URI("https://staging.sample.com"),
            description = "Staging Server",
          ),
          Server(
            url = URI("http://localhost:8080"),
            description = "Development Server",
          ),
        ),
        components = Components(
          securitySchemes = mutableMapOf(
            "auth-basic" to BasicAuth(),
          ),
        ),
      )
    }
    schemaConfigurator = KotlinXSchemaConfigurator()
  }
}
