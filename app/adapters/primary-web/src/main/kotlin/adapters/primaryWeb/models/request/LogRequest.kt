package adapters.primaryWeb.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LogRequest(
  @SerialName("type")
  val type: String,
  @SerialName("details")
  val details: String
)
