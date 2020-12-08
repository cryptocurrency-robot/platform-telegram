package org.freekode.cryptobot.telegramclient.app

import org.freekode.cryptobot.telegramclient.domain.alert.PriceAlertEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service


@Service
class PriceAlertListener {

    private val log: Logger = LoggerFactory.getLogger(PriceAlertListener::class.java)

    @JmsListener(destination = "\${event.priceAlertTopic}")
    fun receiveMessage(event: PriceAlertEvent) {
        log.info("triggered = ${event.type}")
    }
}
