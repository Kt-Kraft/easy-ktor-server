package org.koin.ktor.ext

import io.ktor.server.application.Application
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.ktor.plugin.KOIN_ATTRIBUTE_KEY
import org.koin.ktor.plugin.setKoinApplication

/**
 * Help work on ModuleDefinition
 */
fun Application.getKoin(): Koin =
  attributes.getOrNull(KOIN_ATTRIBUTE_KEY)?.koin ?: run {
    val defaultInstance =
      GlobalContext.getKoinApplicationOrNull() ?: error("No Koin instance started. Use install(Koin) or startKoin()")
    setKoinApplication(defaultInstance)
    attributes[KOIN_ATTRIBUTE_KEY].koin
  }

/**
 * inject lazily given dependency
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> Application.inject(
  qualifier: Qualifier? = null,
  noinline parameters: ParametersDefinition? = null
) =
  lazy { get<T>(qualifier, parameters) }

/**
 * Retrieve given dependency for KoinComponent
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> Application.get(
  qualifier: Qualifier? = null,
  noinline parameters: ParametersDefinition? = null
) =
  getKoin().get<T>(qualifier, parameters)

/**
 * Retrieve given property for KoinComponent
 * @param key - key property
 */
fun <T : Any> Application.getProperty(key: String) =
  getKoin().getProperty<T>(key)

/**
 * Retrieve given property for KoinComponent
 * give a default value if property is missing
 *
 * @param key - key property
 * @param defaultValue - default value if property is missing
 *
 */
fun Application.getProperty(key: String, defaultValue: String) =
  getKoin().getProperty(key) ?: defaultValue
