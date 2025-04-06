package core.usecase

import core.models.Log

public fun interface AddLogUseCase {
  public suspend operator fun invoke(log: Log): Result<Log>
}
