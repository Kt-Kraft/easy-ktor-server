package adapters.primaryWeb

import adapters.primaryWeb.plugins.configureAuthentication
import adapters.primaryWeb.plugins.configureCors
import adapters.primaryWeb.plugins.configureDocumentation
import adapters.primaryWeb.plugins.configureLogging
import adapters.primaryWeb.plugins.configureRequestValidation
import adapters.primaryWeb.plugins.configureRouting
import adapters.primaryWeb.plugins.configureSerialization
import adapters.primaryWeb.plugins.configureStatusPage
import io.ktor.server.application.Application

public fun Application.webBootstrap() {
  configureSerialization()
  configureLogging()
  configureCors()
  configureRequestValidation()
  configureStatusPage()
  configureAuthentication()
  configureDocumentation()
  configureRouting()
}
