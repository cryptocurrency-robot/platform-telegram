package org.freekode.cryptobot.telegramclient.infrastructure.telegram

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

const val CHAT_ID = 273552503

class TelegramPoolingBot(
    private val botToken: String,
    private val botUsername: String,
    private val updateCallback: (Update) -> String?
) : TelegramLongPollingBot() {
    override fun getBotToken(): String = botToken

    override fun getBotUsername(): String = botUsername

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            println("message = ${update.message.text}")
            val result: String = updateCallback(update) ?: return

            val message = SendMessage(update.message.chatId.toString(), result)
            try {
                execute(message)
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
    }
}
