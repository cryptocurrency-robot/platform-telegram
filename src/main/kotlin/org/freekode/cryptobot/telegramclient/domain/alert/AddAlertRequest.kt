package org.freekode.cryptobot.telegramclient.domain.alert

import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
import java.math.BigDecimal


data class AddAlertRequest(
    val indicatorName: String,
    val pair: MarketPair,
    val type: String,
    val value: BigDecimal,
)
