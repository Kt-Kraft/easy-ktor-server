package org.koin.ktor.ext

import io.ktor.server.application.ApplicationCall
import org.koin.core.Koin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

/**
 * Ktor Koin extensions for ApplicationCall class
 *
 * @author Gopal Sharma
 */

/**
 * inject lazily given dependency
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> ApplicationCall.inject(
  qualifier: Qualifier? = null,
  noinline parameters: ParametersDefinition? = null
) =
  lazy { get<T>(qualifier, parameters) }

/**
 * Retrieve given dependency for KoinComponent
 * @param qualifier - bean name / optional
 * @param parameters
 */
inline fun <reified T : Any> ApplicationCall.get(
  qualifier: Qualifier? = null,
  noinline parameters: ParametersDefinition? = null
) =
  getKoin().get<T>(qualifier, parameters)

/**
 * Retrieve given property for KoinComponent
 * @param key - key property
 */
fun <T : Any> ApplicationCall.getProperty(key: String) =
  getKoin().getProperty<T>(key)

/**
 * Retrieve given property for KoinComponent
 * give a default value if property is missing
 *
 * @param key - key property
 * @param defaultValue - default value if property is missing
 *
 */
fun ApplicationCall.getProperty(key: String, defaultValue: String) =
  getKoin().getProperty(key) ?: defaultValue

/**
 * Help work on ModuleDefinition
 */
fun ApplicationCall.getKoin(): Koin = application.getKoin()
