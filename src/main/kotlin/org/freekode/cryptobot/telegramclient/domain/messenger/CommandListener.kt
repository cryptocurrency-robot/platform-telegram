package org.freekode.cryptobot.telegramclient.domain.messenger

interface CommandListener {
    fun getCommand(): String

    fun execute(event: CommandEvent): String
}
