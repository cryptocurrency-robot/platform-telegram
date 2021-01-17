package org.freekode.cryptobot.telegramclient.infrastructure

import org.freekode.cryptobot.telegramclient.domain.alert.AddAlertRequest
import org.freekode.cryptobot.telegramclient.domain.alert.Alert
import org.freekode.cryptobot.telegramclient.domain.alert.AlertRepository
import org.springframework.stereotype.Service
import java.util.function.BiFunction

@Service
class AlertRepositoryImpl(
    private val alertRepositoryClient: AlertRepositoryClient
) : AlertRepository {
    private val alerts: MutableMap<Long, MutableSet<Int>> = mutableMapOf()

    override fun addAlert(request: AddAlertRequest): Alert {
        val newAlert = alertRepositoryClient.addAlert(request)
        val chatId = newAlert.chatId
        val alertId = newAlert.id

        alerts.compute(chatId, BiFunction { _, value ->
            if (value == null) {
                return@BiFunction mutableSetOf(alertId)
            } else {
                value.add(alertId)
                return@BiFunction value
            }
        })
        return newAlert
    }

    override fun getAlerts(chatId: Long): Set<Alert> {
        return alertRepositoryClient.getAlerts(chatId, alerts.getOrDefault(chatId, setOf()))
    }

    override fun findChatIdForAlert(id: Int): Long? {
        return alerts
            .filter { it.value.contains(id) }
            .map { it.key }
            .first()
    }

    override fun removeAlert(chatId: Long, id: Int) {
        throw UnsupportedOperationException("not ready yet")
    }
}
