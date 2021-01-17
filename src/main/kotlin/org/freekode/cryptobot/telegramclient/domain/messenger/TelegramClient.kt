package org.freekode.cryptobot.telegramclient.domain.messenger

import org.freekode.cryptobot.telegramclient.infrastructure.telegram.TelegramPoolingBot
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


@Service
class TelegramClient(
    @Value("\${telegram-bot.token}") private val botToken: String,
    @Value("\${telegram-bot.name}") private val botName: String,
    commandListeners: List<CommandListener>
) {
    private val commandListenerMap = commandListeners.associateBy { it.getCommand() }

    private val telegramPoolingBot = TelegramPoolingBot(botToken, botName) { onUpdate(it) }

    init {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(telegramPoolingBot)
    }

    fun sendMessage(chatId: Long, message: String) {
        val messageRequest = SendMessage(chatId.toString(), message)
        telegramPoolingBot.execute(messageRequest)
    }

    private fun onUpdate(update: Update): String? {
        if (update.message.isCommand) {
            val commandEvent = getCommandEvent(update)
            val commandListener = commandListenerMap[commandEvent.command] ?: return null
            return commandListener.execute(commandEvent)
        } else {
            return "only commands"
        }
    }

    private fun getCommandEvent(update: Update): CommandEvent {
        val split = update.message.text.split(" ")
        val command = split[0].replace("/", "")
        return CommandEvent(update.message.chatId, command, split.subList(1, split.size))
    }
}
