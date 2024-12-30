package adapters.persist

import adapters.persist.repository.LogRepository
import core.models.Log
import core.port.AddLogPort

internal class LogAdapter(
  private val repository: LogRepository
) : AddLogPort {

  override fun addLog(log: Log): Log? {
    return repository.insert(log)
  }
}
