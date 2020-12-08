package org.freekode.cryptobot.telegramclient.domain.messenger

import org.freekode.cryptobot.telegramclient.infrastructure.telegram.TelegramPoolingBot
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


@Service
class TelegramClient(
    @Value("\${telegram-bot.token}") private val botToken: String,
    @Value("\${telegram-bot.name}") private val botName: String
) {

    private val commandListeners = mutableListOf<(CommandEvent) -> String>()

    init {
        val telegramPoolingBot = TelegramPoolingBot(botToken, botName) { onUpdate(it) }

        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(telegramPoolingBot)
    }

    fun addCommandListener(callback: (CommandEvent) -> String) {
        commandListeners.add(callback)
    }

    private fun onUpdate(update: Update) {
        if (update.message.isCommand) {
            val split = update.message.text.split(" ")
            val event = CommandEvent(split[0], split.subList(1, split.size))
            commandListeners.forEach { it(event) }
        } else {
            throw IllegalStateException("only commands")
        }
    }
}
