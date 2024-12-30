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
  implementation(libs.koin.core)
  implementation(libs.typesafe.config)
  implementation(libs.logback.classic)
}
