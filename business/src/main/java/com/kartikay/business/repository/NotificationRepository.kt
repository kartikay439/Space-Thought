package com.kartikay.business.repository

import com.kartikay.business.model.notification.AppNotification
import kotlinx.coroutines.flow.SharedFlow

interface NotificationRepository {
    fun streamNotifications(): SharedFlow<AppNotification>
    suspend fun connect()
    suspend fun connectSafely()
}


