plugins {
  `java-library`
  alias(libs.plugins.kotlin.jvm)
}

kotlin {
  explicitApi()
  jvmToolchain(21)
}

dependencies {
  // Others
  implementation(libs.koin.core)
}
