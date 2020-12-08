package org.freekode.cryptobot.telegramclient.domain.alert

import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
import org.freekode.cryptobot.telegramclient.domain.price.PlatformName
import java.io.Serializable
import java.math.BigDecimal


data class PriceAlertEvent(
    val id: Int,
    val platform: PlatformName,
    val pair: MarketPair,
    val value: BigDecimal,
    val type: String,
) : Serializable
