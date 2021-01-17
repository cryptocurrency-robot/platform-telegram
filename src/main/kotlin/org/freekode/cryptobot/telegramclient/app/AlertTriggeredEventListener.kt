package org.freekode.cryptobot.telegramclient.app

import org.freekode.cryptobot.telegramclient.domain.alert.AlertRepository
import org.freekode.cryptobot.telegramclient.domain.alert.AlertTriggeredEvent
import org.freekode.cryptobot.telegramclient.domain.messenger.TelegramClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service

@Service
class AlertTriggeredEventListener(
    private val alertRepository: AlertRepository,
    private val telegramClient: TelegramClient
) {

    private val log: Logger = LoggerFactory.getLogger(AlertTriggeredEventListener::class.java)

    @JmsListener(destination = "\${event.topic.alertTriggered}")
    fun onAlertTriggered(event: AlertTriggeredEvent) {
        log.info("alert triggered = $event")
        val chatId = alertRepository.findChatIdForAlert(event.id)
        if (chatId != null) {
            telegramClient.sendMessage(chatId, event.toString())
        }
    }
}
