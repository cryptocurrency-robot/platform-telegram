package org.freekode.cryptobot.priceservice.infrastructure

import org.freekode.cryptobot.priceservice.domain.PriceEventRepository
import org.freekode.cryptobot.priceservice.domain.PriceValueEvent
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class InMemoryPriceEventRepository : PriceEventRepository {
    private val storage: MutableMap<Long, PriceValueEvent> = HashMap()

    override fun addPrice(priceValueEvent: PriceValueEvent) {
        storage[priceValueEvent.timestamp] = priceValueEvent
    }

    override fun getPrices(startDate: LocalDateTime, endDate: LocalDateTime): List<PriceValueEvent> {
        return ArrayList()
    }
}
