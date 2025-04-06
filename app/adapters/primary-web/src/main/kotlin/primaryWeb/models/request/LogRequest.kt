package primaryWeb.models.request

import core.models.Log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LogRequest(
  @SerialName("type")
  val type: String,
  @SerialName("details")
  val details: String
)

public fun LogRequest.toLog() = Log(
  type = this.type,
  details = this.details,
)
