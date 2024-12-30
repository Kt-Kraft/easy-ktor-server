package core.internal

import core.models.Log
import core.port.AddLogPort
import core.port.PersistTransactionPort
import core.usecase.AddLogUseCase

internal class AddLogInternal(
  private val addLogPort: AddLogPort,
  private val txPort: PersistTransactionPort,
) : AddLogUseCase {

  override suspend fun invoke(log: Log) = txPort.withNewTransaction {
    addLogPort.addLog(log)
  }
}
