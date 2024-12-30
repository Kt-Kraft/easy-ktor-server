package org.koin.ktor.plugin

import org.koin.core.Koin
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.mp.KoinPlatformTools
import org.koin.mp.generateId

class RequestScope(private val _koin: Koin) : KoinScopeComponent {
  private val scopeId = "request_" + KoinPlatformTools.generateId()
  override fun getKoin(): Koin = _koin
  override val scope = createScope(scopeId = scopeId)
}
