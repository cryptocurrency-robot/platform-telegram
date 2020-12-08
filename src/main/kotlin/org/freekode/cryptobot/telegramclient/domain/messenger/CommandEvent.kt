package org.freekode.cryptobot.telegramclient.domain.messenger

data class CommandEvent(
    val command: String,
    val params: List<String>
)
