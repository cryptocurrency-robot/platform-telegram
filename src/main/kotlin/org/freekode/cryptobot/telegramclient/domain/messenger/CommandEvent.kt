package org.freekode.cryptobot.telegramclient.domain.messenger

data class CommandEvent(
    val chatId: Long,
    val command: String,
    val params: List<String>
)
