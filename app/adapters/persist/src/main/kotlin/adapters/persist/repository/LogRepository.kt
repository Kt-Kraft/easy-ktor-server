package adapters.persist.repository

import adapters.persist.table.LogTable
import core.models.Log
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

internal class LogRepository {

  fun insert(log: Log): Log? {
    return LogTable
      .insert {
        it[type] = log.type
        it[details] = log.details
      }
      .resultedValues
      ?.singleOrNull()
      ?.let(::resultRowToNode)
  }

  // --------------------
  // Mappers
  // --------------------

  private fun resultRowToNode(row: ResultRow) = Log(
    id = row[LogTable.id].value,
    type = row[LogTable.type],
    details = row[LogTable.details],
    createdAt = row[LogTable.createdAt],
  )
}
