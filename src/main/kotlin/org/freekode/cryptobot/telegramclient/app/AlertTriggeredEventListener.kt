package org.freekode.cryptobot.telegramclient.app

import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.freekode.cryptobot.telegramclient.domain.alert.AlertTriggeredEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.function.Consumer

@Service
class AlertTriggeredEventListener {

    private val log: Logger = LoggerFactory.getLogger(AlertTriggeredEventListener::class.java)

    private val rateLimitedOnAlertTriggered: Consumer<AlertTriggeredEvent>

    private val rateLimiter: RateLimiter

    init {
        val rateLimiterConfig = getRateLimiterConfig()
        val rateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig)
        rateLimiter = rateLimiterRegistry.rateLimiter("main")
        rateLimitedOnAlertTriggered = RateLimiter.decorateConsumer(rateLimiter) { rateLimited(it) }
    }

    @JmsListener(destination = "\${event.topic.alertTriggered}")
    fun onAlertTriggered(event: AlertTriggeredEvent) {
        rateLimitedOnAlertTriggered.accept(event)
    }

    private fun rateLimited(event: AlertTriggeredEvent) {
        log.info("alert triggered = $event")
    }


    private fun getRateLimiterConfig() = RateLimiterConfig.custom()
        .limitRefreshPeriod(Duration.ofSeconds(1))
        .limitForPeriod(1)
        .build()
}
