package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.utils.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

fun Route.tomorrowRouting() {
    route("/tomorrow") {

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )

            //Get redis
            val info = getKey(id)

            if (info.isNotEmpty()) {

                //call.respondText(info)
                val data = Json.decodeFromString<Data1>(info)
                call.respond(data)
            } else {
                call.respondText("No data found", status = HttpStatusCode.NotFound)
            }
        }
    }
}