plugins {
  `java-library`
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
  explicitApi()
  jvmToolchain(21)
}

dependencies {
  // Projects
  implementation(projects.app.core)

  // Others
  implementation(libs.bundles.ktor.client)
  implementation(libs.koin.core)
  implementation(libs.logback.classic)
  testImplementation(libs.kotlin.test.junit)
  testImplementation(libs.kotlin.test)
}
