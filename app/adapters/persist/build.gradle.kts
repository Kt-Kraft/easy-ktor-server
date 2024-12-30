plugins {
  `java-library`
  alias(libs.plugins.kotlin.jvm)
}

kotlin {
  explicitApi()
  jvmToolchain(21)
}

dependencies {
  // Projects
  implementation(projects.app.core)

  // Others
  implementation(libs.bundles.exposed)
  implementation(libs.koin.core)
  implementation(libs.postgresql)
  implementation(libs.zaxxer.hikaricp)
  implementation(libs.flyway.core)
  implementation(libs.flyway.database.postgresql)
  implementation(libs.logback.classic)
  testImplementation(libs.kotlin.test.junit)
  testImplementation(libs.kotlin.test)
}
