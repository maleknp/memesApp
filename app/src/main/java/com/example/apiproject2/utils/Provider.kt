package com.example.apiproject2.utils

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * Created by Taha Ben Ashur (https://github.com/tahaak67) on 08,Feb,2023
 */
object Provider {

    val client = HttpClient(Android) {
        //Makes the requests thorow an exception if the http status code is does not start with 2
        expectSuccess = true

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        //to map json objects returned from the api to a kotlin data class
        install(ContentNegotiation) {
            json(Json {
                //ignores json keys we have not included in our data class
                ignoreUnknownKeys = true
            })
        }
        //a logger to see logging information about every request we make using the client
        install(Logging) {
            level = LogLevel.ALL
        }

    }
}