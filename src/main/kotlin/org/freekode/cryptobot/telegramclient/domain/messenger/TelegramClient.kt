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
    @Value("\${telegram-bot.name}") private val botName: String,
    private val commandListeners: List<CommandListener>
) {

    private val commandListenerMap = commandListeners.associateBy { it.getCommand() }

    init {
        val telegramPoolingBot = TelegramPoolingBot(botToken, botName) { onUpdate(it) }

        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(telegramPoolingBot)
    }

    private fun onUpdate(update: Update): String {
        if (update.message.isCommand) {
            val commandEvent = getCommandEvent(update)
            val commandListener = commandListenerMap[commandEvent.command] ?: return "can't find command listener for command = ${commandEvent.command}"
            return commandListener.execute(commandEvent)
        } else {
            return "only commands"
        }
    }

    private fun getCommandEvent(update: Update): CommandEvent {
        val split = update.message.text.split(" ")
        val command = split[0].replace("/", "")
        return CommandEvent(command, split.subList(1, split.size))
    }
}
