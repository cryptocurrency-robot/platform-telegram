package org.freekode.cryptobot.priceservice.app

import org.freekode.cryptobot.priceservice.domain.PriceEventRepository
import org.freekode.cryptobot.priceservice.domain.PriceValueEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service


@Service
class MarketPriceListener(
    private val priceEventRepository: PriceEventRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(MarketPriceListener::class.java)

    @JmsListener(destination = "\${event.priceQueueName}")
    fun receiveMessage(event: PriceValueEvent) {
        logger.info("message = ${event.price}")
    }
}
