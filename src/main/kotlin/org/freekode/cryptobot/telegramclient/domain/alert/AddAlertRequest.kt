package org.freekode.cryptobot.telegramclient.domain.alert

import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
import java.math.BigDecimal


data class AddAlertRequest(
    val chatId: Long,
    val indicatorName: String,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
)
