package org.koin.ktor.plugin

import io.ktor.events.EventDefinition
import org.koin.core.KoinApplication

/**
 * Event definition for [KoinApplication] Started event
 */
val KoinApplicationStarted = EventDefinition<KoinApplication>()

/**
 * Event definition for an event that is fired when the [KoinApplication] is going to stop
 */
val KoinApplicationStopPreparing = EventDefinition<KoinApplication>()

/**
 * Event definition for [KoinApplication] Stopping event
 */
val KoinApplicationStopped = EventDefinition<KoinApplication>()
