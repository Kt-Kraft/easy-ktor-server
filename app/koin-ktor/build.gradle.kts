plugins {
  `java-library`
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
  jvmToolchain(21)
}

dependencies {
  implementation(libs.bundles.ktor)
  implementation(libs.koin.core)
}
