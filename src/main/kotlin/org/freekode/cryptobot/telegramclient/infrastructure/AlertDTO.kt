package org.freekode.cryptobot.telegramclient.infrastructure

import org.freekode.cryptobot.telegramclient.domain.alert.Alert
import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
import java.math.BigDecimal


data class AlertDTO(
    val id: Int,
    val indicatorName: String,
    val pair: MarketPair,
    val type: String,
    val value: BigDecimal,
) {
    fun toDomain(chatId: Long): Alert {
        return Alert(id, chatId, indicatorName, pair, type, value)
    }
}
