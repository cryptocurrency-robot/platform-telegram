package org.freekode.cryptobot.priceservice.domain

import java.io.Serializable
import java.math.BigDecimal


data class PriceValueEvent(
    val platformName: PlatformName,
    val pair: MarketPair,
    val price: BigDecimal,
    val timestamp: Long
) : Serializable
