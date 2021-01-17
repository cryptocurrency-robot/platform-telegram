package org.freekode.cryptobot.telegramclient.infrastructure

import org.freekode.cryptobot.telegramclient.domain.alert.AddAlertRequest
import org.freekode.cryptobot.telegramclient.domain.alert.Alert
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import java.net.URI

const val ALERT_PATH = "/alert"

@Service
class AlertRepositoryClient(
    @Value("\${priceServiceUrl}") private val priceServiceUrl: String
) {
    private val webClient = WebClient.create(priceServiceUrl)

    fun addAlert(request: AddAlertRequest): Alert {
        return webClient
            .post()
            .uri { uriBuilder -> buildUri(uriBuilder, ALERT_PATH) }
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(AddAlertRequestDTO(request)))
            .retrieve()
            .bodyToMono(AlertDTO::class.java)
            .map { it.toDomain(request.chatId) }
            .block()!!
    }

    fun getAlerts(chatId: Long, ids: Set<Int>): Set<Alert> {
        webClient
            .get()
            .uri { uriBuilder -> buildUri(uriBuilder, "$ALERT_PATH/$ids") }
            .retrieve()
            .toBodilessEntity()
            .block()
        throw UnsupportedOperationException("not ready yet")
    }

    fun removeAlert(id: Int) {
        webClient
            .delete()
            .uri { uriBuilder -> buildUri(uriBuilder, "$ALERT_PATH/$id") }
            .retrieve()
            .toBodilessEntity()
            .block()
    }

    private fun buildUri(uriBuilder: UriBuilder, relativePath: String): URI {
        return uriBuilder
            .path(relativePath)
            .build()
    }
}
