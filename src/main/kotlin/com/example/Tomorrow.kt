package com.example

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*
import com.example.utils.*
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import java.time.LocalDateTime

suspend fun main(args: Array<String>) {

    val location = Location.valueOf(args[0])

    getTomorrow(location)
}

suspend fun getTomorrow(location: Location) {

    val log = "log-$location"
    val rnds = (1..10).random()

    println(rnds)

    if (rnds in 3..10) {


        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        client.plugin(HttpSend).intercept { request ->
            val originalCall = execute(request)
            if (originalCall.response.status.value !in 100..399) {

                fail(log, "The API Request Failed")
                execute(request)
            } else {
                originalCall
            }
        }

        val httpResponse: HttpResponse = client.get(System.getenv("APIURL")) {
            url {
                parameters.append("apikey", System.getenv("APIKEY"))
                parameters.append("fields", System.getenv("FIELDS"))
                parameters.append("timesteps", System.getenv("TIMESTEPS"))
                parameters.append("location", location.descripcion())
                parameters.append("units", System.getenv("UNITS"))
                parameters.append("timezone", System.getenv("TIMEZONE"))
            }
        }

        client.close()

        if (httpResponse.status.value in 200..299) {

            val responseBody: String = httpResponse.body()

            setKey(location.toString(), responseBody)
        } else {
            fail(log, "The API Request Failed")
        }

    } else {
        fail(log, "The API Request Failed - Generado por probabilidad de fallo 20%'")
    }
}

suspend fun fail(key: String, message: String) {

    val current = key + "-" + LocalDateTime.now().toString()
    val log = "timestamp: $current -  message: $message "
    setKey(current, log)
}