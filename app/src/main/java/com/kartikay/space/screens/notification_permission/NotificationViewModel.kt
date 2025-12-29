package com.kartikay.space.screens.notification_permission

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartikay.business.repository.NotificationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class NotificationViewModel(
    private val notificationRepository: NotificationRepository
) : ViewModel() {

    fun startListning(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            notificationRepository.connect()
        }

        viewModelScope.launch {
//            NotificationHelper.show(context, "Success", "Logged in successfully")

            notificationRepository.streamNotifications().collect { notification ->
                NotificationHelper.show(context, notification.title, notification.message)
            }
//            notificationRepository.connectSafely()
        }
    }

}