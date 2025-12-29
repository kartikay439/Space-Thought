package com.kartikay.data.network.notification_socket

import android.util.Log
import com.kartikay.business.model.notification.AppNotification
import com.kartikay.data.model.notification.NotificationDTo
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readReason
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.serialization.json.Json

class NotificationSockerService(
    private val client: HttpClient
) {
    private val _events = MutableSharedFlow<AppNotification>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()



    suspend fun connect() {

        Log.d("Socket", "🚀 Notification service initiated")

        try {
            Log.d(
                "Socket",
                "🌐 Connecting to ws://10.63.167.117:8000/notifications"
            )

            client.webSocket(
                method = HttpMethod.Get,
                host = "10.63.167.117",
                port = 8080,
                path = "/notifications"
            ) {

                Log.d("Socket", "✅ WebSocket CONNECTED")

                for (frame in incoming) {

                    when (frame) {
                        is Frame.Text -> {
                            val text = frame.readText()
                            Log.d("Socket", "📩 Message received: $text")

                            val notification =
                                Json.decodeFromString<NotificationDTo>(text)

                            _events.emit(notification.toNotification())
                        }

                        is Frame.Close -> {
                            Log.w("Socket", "⚠️ WebSocket CLOSED: ${frame.readReason()}")
                        }

                        else -> {
                            Log.d("Socket", "ℹ️ Ignored frame: ${frame.frameType}")
                        }
                    }
                }

                Log.w("Socket", "🔌 Incoming channel closed")
            }

        } catch (e: Exception) {
            Log.e(
                "Socket",
                "❌ WebSocket connection FAILED: ${e.message}",
                e
            )
        }
    }

    suspend fun connectSafely() {
        Log.d("Socket", "Trying to connect...")

        try {
            client.webSocket(
                host = "10.63.167.117",
                port = 8080,
                path = "/notifications"
            ) {
                Log.d("Socket", "Connected!")
            }
        } catch (e: Exception) {
            Log.e("Socket", "Connection failed", e)
        }
    }



}