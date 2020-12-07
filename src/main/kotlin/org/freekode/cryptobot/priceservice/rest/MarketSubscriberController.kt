package org.freekode.cryptobot.priceservice.rest

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("market/subscriber")
class MarketSubscriberController(
) {
    @GetMapping
    fun uploadFile(): String {
        return "OK"
    }

    @PostMapping("/subscribe/{pair}")
    fun subscribeForPair(@PathVariable pair: MarketPair) {
    }
}
