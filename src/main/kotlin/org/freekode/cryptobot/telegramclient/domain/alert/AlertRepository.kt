package org.freekode.cryptobot.telegramclient.domain.alert


interface AlertRepository {
    fun addAlert(request: AddAlertRequest): Alert

    fun getAlerts(ids: Set<Int>): Set<Alert>

    fun removeAlert(id: Int)
}
