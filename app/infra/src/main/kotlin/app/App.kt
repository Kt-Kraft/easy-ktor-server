package app

import adapters.env.di.envModule
import adapters.persist.di.persistModule
import adapters.primaryWeb.di.primaryWebModule
import adapters.primaryWeb.webBootstrap
import adapters.remoting.remotingModule
import core.di.coreModule
import core.port.BootPersistStoragePort
import core.port.ShutdownPersistStoragePort
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import kotlinx.coroutines.runBlocking
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

public fun main(args: Array<String>) {
  return EngineMain.main(args)
}

public fun Application.main() {
  install(Koin) {
    modules(
      coreModule,
      envModule,
      persistModule,
      remotingModule,
      primaryWebModule,
    )
  }

  val shutdownStoragePort by inject<ShutdownPersistStoragePort>()
  val bootPersistStoragePort by inject<BootPersistStoragePort>()

  monitor.subscribe(ApplicationStopped) {
    shutdownStoragePort.shutdownStorage()
  }

  runBlocking {
    bootPersistStoragePort.bootStorage {}
  }

  webBootstrap()
}
