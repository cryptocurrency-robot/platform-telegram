package org.freekode.cryptobot.telegramclient.rest

import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
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
