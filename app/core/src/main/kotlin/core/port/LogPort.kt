package core.port

import core.models.Log

public interface AddLogPort {
  public fun addLog(log: Log): Log?
}
