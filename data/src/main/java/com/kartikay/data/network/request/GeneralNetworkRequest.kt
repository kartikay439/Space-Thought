package com.kartikay.data.network.request

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

suspend inline fun <reified T> makeHttpRequest(
    client: HttpClient,
    url: String,
    method: HttpMethod,
    body: Any? = null,
    headers: Map<String, String?> = emptyMap(),
    params: Map<String, String?> = emptyMap()
): T {
    return client.request(url) {
        this.method = method

        headers.forEach { (key, value) ->
            header(key, value)
        }
        url {
            params.forEach { (key, value) ->
                value?.let { parameters.append(key, it) }
            }
        }

        if (body != null) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }.body()
}
