package com.example.voyatekgroup.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.sse.SSE
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json

fun createClient(
    engine: HttpClientEngine,
) =
    HttpClient(engine) {
        install(SSE)
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "ca3357428fc3ca1882b5.free.beeceptor.com"
                path("/api")
            }
            contentType(ContentType.Application.Json)
        }
    }