package com.myapp.amateurballparkreviewsapi.common.util

import com.sendgrid.Client
import com.sendgrid.Request
import com.sendgrid.SendGrid
import org.apache.http.impl.client.HttpClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.net.HttpURLConnection

@Service
class SendMailUtils {
    @Value("\${abl.sendgrid.apikey:#{null}}")
    private var sendGridKey: String? = null

    fun sendTemplateMail(request: Request): Int {
        val sendGrid = SendGrid(sendGridKey, prepareClient())
        val resp = sendGrid.api(request)
        if (resp.statusCode < 200 || resp.statusCode >= 400) {
            throw RuntimeException()
        }
        return HttpURLConnection.HTTP_OK
    }

    private fun prepareClient(): Client {
        val httpClientBuilder = HttpClients.custom().useSystemProperties()
        return Client(httpClientBuilder.build())
    }
}
