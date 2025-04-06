package primaryWeb.plugins

import core.usecase.GetBasicCredentialUseCase
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.basic
import org.koin.ktor.ext.inject

internal fun Application.configureAuthentication() {
  val getBasicCredential by inject<GetBasicCredentialUseCase>()
  val basicCredential = getBasicCredential()

  install(Authentication) {
    basic(name = "auth-basic") {
      validate { credentials ->
        val credentialsMatch = credentials.name == basicCredential.username &&
          credentials.password == basicCredential.password
        if (credentialsMatch) UserIdPrincipal(credentials.name) else null
      }
    }
  }
}
