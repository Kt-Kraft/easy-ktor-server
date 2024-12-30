package core.models

import java.time.LocalDateTime

public data class Log(
  val id: Long? = null,
  val type: String? = null,
  val details: String? = null,
  val createdAt: LocalDateTime? = null,
)
