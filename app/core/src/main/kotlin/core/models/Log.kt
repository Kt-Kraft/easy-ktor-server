package core.models

public data class Log(
  val id: Long? = null,
  val type: String,
  val details: String,
  val createdAt: String? = null,
)
