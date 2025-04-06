package core.models

import java.time.LocalDateTime

public data class Log(
  val id: Long? = null,
  val type: String,
  val details: String,
  val createdAt: LocalDateTime? = null,
)
