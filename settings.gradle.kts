rootProject.name = "easy-ktor-server"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    maven(url = "https://maven.pkg.github.com/Kt-Kraft/build-logic/") {
      credentials {
        username = System.getenv("GITHUB_USERNAME")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
  }
}

plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

include(":app:core")
include(":app:infra")

include(":app:adapters:env")
include(":app:adapters:persist")
include(":app:adapters:remoting")
include(":app:adapters:primary-web")
