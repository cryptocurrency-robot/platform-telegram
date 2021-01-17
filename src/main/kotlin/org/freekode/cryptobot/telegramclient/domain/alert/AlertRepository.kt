package org.freekode.cryptobot.telegramclient.domain.alert


interface AlertRepository {
    fun addAlert(request: AddAlertRequest): Alert

    fun getAlerts(chatId: Long): Set<Alert>

    fun findChatIdForAlert(id: Int): Long?

    fun removeAlert(chatId: Long, id: Int)
}
