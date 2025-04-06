package persist.table

import java.time.LocalDateTime
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

internal object LogTable : LongIdTable(name = "logs", columnName = "log_id") {
  val type = varchar("type", 255)
  val details = text("details")
  val createdAt = datetime(name = "created_at")
    .clientDefault {
      LocalDateTime.now()
    }
}
