package org.freekode.cryptobot.telegramclient.domain.price

import java.io.Serializable
import java.math.BigDecimal


data class CurrentPriceEvent(
    val platformName: PlatformName,
    val pair: MarketPair,
    val price: BigDecimal,
    val timestamp: Long
) : Serializable

