package com.example.utils

import io.github.crackthecodeabhi.kreds.connection.Endpoint
import io.github.crackthecodeabhi.kreds.connection.newClient

suspend fun setKey(key: String, value: String) {

    val url = String.format("%s:%s", System.getenv("REDIS_HOST"), System.getenv("REDIS_PORT"))

    newClient(Endpoint.from(url)).use { client ->
        client.auth(System.getenv("REDIS_PASSWORD"))
        client.set(key, value)
        client.expire(key, 300u) // set expiration to 5 minutos
    }
}

suspend fun getKey(key: String): String {

    val url = String.format("%s:%s", System.getenv("REDIS_HOST"), System.getenv("REDIS_PORT"))
    var value = ""

    newClient(Endpoint.from(url)).use { client ->
        client.auth(System.getenv("REDIS_PASSWORD"))
        value = client.get(key).toString()
    }

    return value
}
