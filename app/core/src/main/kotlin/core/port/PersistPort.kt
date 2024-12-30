package core.port

@RequiresOptIn(message = "This API is required to be called from existing transaction context.")
@Retention(AnnotationRetention.BINARY)
@Target(
  AnnotationTarget.CLASS,
  AnnotationTarget.PROPERTY,
  AnnotationTarget.LOCAL_VARIABLE,
  AnnotationTarget.VALUE_PARAMETER,
  AnnotationTarget.CONSTRUCTOR,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER,
  AnnotationTarget.TYPEALIAS
)
public annotation class RequiresTransactionContext

public interface BootPersistStoragePort {
  public suspend fun <T> bootStorage(preInit: suspend () -> T)
}

public interface ShutdownPersistStoragePort {
  public fun shutdownStorage()
}

public interface PersistTransactionPort {
  /** Request new transaction */
  public suspend fun <T> withNewTransaction(block: suspend () -> T): T

  /** Request to run inside already started transaction */
  @RequiresTransactionContext
  public suspend fun <T> withExistingTransaction(block: suspend () -> T): T

  /** Request to starts new transaction if it has not been started yet or run in existing transaction */
  @RequiresTransactionContext
  public suspend fun <T> withTransaction(block: suspend () -> T): T
}
