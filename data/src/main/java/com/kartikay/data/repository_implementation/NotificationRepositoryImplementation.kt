package com.kartikay.data.repository_implementation

import com.kartikay.business.model.notification.AppNotification
import com.kartikay.business.repository.NotificationRepository
import com.kartikay.data.network.notification_socket.NotificationSockerService
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map

class NotificationRepositoryImplementation(
    private val notificationSocketService: NotificationSockerService
) : NotificationRepository {
    override fun streamNotifications(): SharedFlow<AppNotification> {
        return notificationSocketService.events
    }

    override suspend fun connect() {
        notificationSocketService.connect()
    }

    override suspend fun connectSafely() = notificationSocketService.connectSafely()
}