package persist.adapter

import persist.table.LogTable
import persist.table.LogTable.details
import persist.table.LogTable.type
import core.models.Log
import core.port.AddLogPort
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

internal class LogAdapter : AddLogPort {

  override fun addLog(log: Log): Result<Log> {
    return runCatching {
      val resultRow = LogTable
        .insert {
          it[type] = log.type
          it[details] = log.details
        }
        .resultedValues
        ?.singleOrNull()
        ?: throw Exception("Insert succeeded but no result returned")

      resultRowToNode(resultRow)
    }
  }

  private fun resultRowToNode(row: ResultRow) = Log(
    id = row[LogTable.id].value,
    type = row[type],
    details = row[details],
    createdAt = row[LogTable.createdAt],
  )
}
