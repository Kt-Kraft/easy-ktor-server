package org.koin.ktor.plugin

import io.ktor.server.application.*
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi

@OptIn(KoinInternalApi::class)
val KoinIsolated = createApplicationPlugin(name = "Koin", createConfiguration = { KoinApplication.init() }) {
  val koinApplication = setupKoinApplication()
  setupMonitoring(koinApplication)
  setupKoinScope(koinApplication)
  koinApplication.koin.logger.info("Koin is using Ktor isolated context")
}
