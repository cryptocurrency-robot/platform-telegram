package org.freekode.cryptobot.priceservice.domain

import java.time.LocalDateTime


interface PriceEventRepository {
    fun addPrice(priceValueEvent: PriceValueEvent)

    fun getPrices(startDate: LocalDateTime, endDate: LocalDateTime): List<PriceValueEvent>
}
