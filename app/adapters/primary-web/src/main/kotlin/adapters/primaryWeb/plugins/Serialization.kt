package adapters.primaryWeb.plugins

import io.bkbn.kompendium.oas.serialization.KompendiumSerializersModule
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json

internal fun Application.configureSerialization() {
  install(ContentNegotiation) {
    json(
      Json {
        serializersModule = KompendiumSerializersModule.module
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
      },
    )
  }
}
