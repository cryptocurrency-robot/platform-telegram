package org.freekode.cryptobot.telegramclient.domain.alert

import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
import java.io.Serializable
import java.math.BigDecimal


data class AlertTriggeredEvent(
    val id: Int,
    val indicatorName: String,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
) : Serializable
