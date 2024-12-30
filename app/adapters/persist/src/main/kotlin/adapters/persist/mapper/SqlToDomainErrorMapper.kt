package adapters.persist.mapper

import adapters.persist.errors.DatabaseErrorInspector
import adapters.persist.errors.DatabaseErrorState
import core.errors.ResourceAlreadyExistsException
import org.jetbrains.exposed.exceptions.ExposedSQLException

internal fun ExposedSQLException.throwAsDomainException(inspector: DatabaseErrorInspector): Nothing {
  if (inspector.errorState(e = this) == DatabaseErrorState.DUPLICATE_KEY_VALUE_VIOLATES_UNIQUE_CONSTRAINT) {
    throw ResourceAlreadyExistsException("Resource already exists", this.message ?: this.toString())
  }
  throw this
}
