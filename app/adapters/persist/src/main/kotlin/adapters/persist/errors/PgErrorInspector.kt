package adapters.persist.errors

import org.jetbrains.exposed.exceptions.ExposedSQLException

internal class PgErrorInspector : DatabaseErrorInspector {
  override fun errorState(e: ExposedSQLException): DatabaseErrorState =
    if (e.sqlState == "23505") {
      DatabaseErrorState.DUPLICATE_KEY_VALUE_VIOLATES_UNIQUE_CONSTRAINT
    } else {
      DatabaseErrorState.OTHER
    }
}
