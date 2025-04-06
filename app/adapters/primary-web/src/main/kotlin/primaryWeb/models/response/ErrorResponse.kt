package primaryWeb.models.response

import core.errors.DomainException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorResponse(
  @SerialName("title")
  val title: String,
  @SerialName("detail")
  val detail: String,
  @SerialName("specifics")
  val specifics: List<String>,
)

internal fun DomainException.toErrorResponse() =
  ErrorResponse(title, detail, specifics)
