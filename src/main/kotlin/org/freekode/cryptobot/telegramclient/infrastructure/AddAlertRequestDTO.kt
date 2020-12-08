package org.freekode.cryptobot.telegramclient.infrastructure

import org.freekode.cryptobot.telegramclient.domain.alert.AddAlertRequest
import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
import java.math.BigDecimal


data class AddAlertRequestDTO(
    val indicatorName: String,
    val pair: MarketPair,
    val type: String,
    val value: BigDecimal,
) {
    constructor(request: AddAlertRequest) : this(request.indicatorName, request.pair, request.type, request.value)
}
