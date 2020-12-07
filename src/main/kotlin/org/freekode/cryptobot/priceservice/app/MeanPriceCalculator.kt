package org.freekode.cryptobot.priceservice.app

import org.freekode.cryptobot.priceservice.domain.PriceEventRepository
import org.freekode.cryptobot.priceservice.domain.PriceValueEvent
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime


@Service
class MeanPriceCalculator(
    private val priceEventRepository: PriceEventRepository,
) {

    @Scheduled(fixedDelay = 30_000, initialDelay = 30_000)
    fun average30sPrice() {
        val endDate = LocalDateTime.now()
        val startDate = endDate.minusSeconds(30)
        val prices = priceEventRepository.getPrices(startDate, endDate)
        val meanPrice = getMeanPrice(prices)
    }

    @Scheduled(fixedDelay = 60_000, initialDelay = 60_000)
    fun average1minPrice() {
    }

    private fun getMeanPrice(priceEvents: List<PriceValueEvent>): BigDecimal {
        var price = BigDecimal.ZERO
        priceEvents.forEach { price = price.add(it.price) }
        return price.divide(BigDecimal(priceEvents.size))
    }
}
