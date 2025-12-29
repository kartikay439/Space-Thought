package com.kartikay.data.model.notification

import com.kartikay.business.model.notification.AppNotification
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDTo(
    val title: String,
    val message: String,
) {
    fun toNotification(): AppNotification {
        return AppNotification(
            title = title,
            message = message
        )

    }
}
