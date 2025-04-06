package primaryWeb

import primaryWeb.plugins.configureAuthentication
import primaryWeb.plugins.configureCors
import primaryWeb.plugins.configureDocumentation
import primaryWeb.plugins.configureLogging
import primaryWeb.plugins.configureRequestValidation
import primaryWeb.plugins.configureRouting
import primaryWeb.plugins.configureSerialization
import primaryWeb.plugins.configureStatusPage
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
