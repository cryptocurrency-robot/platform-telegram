package org.freekode.cryptobot.telegramclient.domain.alert

import org.freekode.cryptobot.telegramclient.domain.messenger.CommandEvent
import org.freekode.cryptobot.telegramclient.domain.messenger.CommandListener
import org.freekode.cryptobot.telegramclient.domain.price.MarketPair
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class AddAlertCommandListener(private val alertRepository: AlertRepository) : CommandListener {
    override fun getCommand(): String = "addAlert"

    override fun execute(event: CommandEvent): String {
        println("event add = $event")
        val indicatorName = event.params[0]
        val pair = MarketPair.valueOf(event.params[1])
        val type = AlertType.valueOf(event.params[2])
        val value = BigDecimal.valueOf(event.params[3].toDouble())
        val request = AddAlertRequest(event.chatId, indicatorName, pair, type, value)
        val alert = alertRepository.addAlert(request)

        return "New alert id ${alert.id}"
    }
}
