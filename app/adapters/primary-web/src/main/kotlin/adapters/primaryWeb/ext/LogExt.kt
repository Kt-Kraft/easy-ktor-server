package adapters.primaryWeb.ext

import io.ktor.server.application.ApplicationCall

public fun ApplicationCall.logDebug(vararg message: String) {
  application.environment.log.debug(message.joinToString(" "))
}
