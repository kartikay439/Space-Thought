package com.example.data.network.request

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod

suspend inline fun<reified T> makeHttpRequest(
    client: HttpClient,
    body:Any?=null,
    headers:Map<String,String>?= emptyMap(),
    httpMethod:HttpMethod
):T{
    val reponse:T = client.request {

        method = httpMethod

        headers?.map {
            (key,value) ->
            header(key,value)
        }
        setBody(
            body
        )
    }.body()
    return  reponse
}