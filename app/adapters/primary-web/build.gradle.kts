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
  implementation(projects.app.koinKtor)

  // Others
  implementation(libs.bundles.ktor)
  implementation(libs.koin.core)
  implementation(libs.kopendium.core)
  implementation(libs.logback.classic)
  testImplementation(libs.kotlin.test.junit)
  testImplementation(libs.kotlin.test)
}
