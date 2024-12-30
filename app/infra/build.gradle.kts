plugins {
  application
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.ktor.plugin)
  alias(libs.plugins.shadow)
}

kotlin {
  explicitApi()
  jvmToolchain(21)
}

version = "0.1.0"

application {
  mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks {
  shadowJar {
    manifest {
      attributes("Main-Class" to "app.AppKt")
    }
  }
}

tasks.register("stage") {
  dependsOn("installDist")
}

dependencies {
  // Projects
  implementation(projects.app.core)
  implementation(projects.app.adapters.env)
  implementation(projects.app.adapters.persist)
  implementation(projects.app.adapters.remoting)
  implementation(projects.app.adapters.primaryWeb)
  implementation(projects.app.koinKtor)

  // Others
  implementation(libs.bundles.ktor)
  implementation(libs.koin.core)
  implementation(libs.kopendium.core)
  implementation(libs.logback.classic)
  testImplementation(libs.kotlin.test.junit)
  testImplementation(libs.kotlin.test)
}
