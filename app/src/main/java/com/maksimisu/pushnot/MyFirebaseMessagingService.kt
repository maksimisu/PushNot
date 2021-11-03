package com.maksimisu.pushnot

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TOKEN", "New token $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val intent = Intent(INTENT_FILTER)
        intent.putExtra(EXTRA_TITLE, message.notification?.title)
        intent.putExtra(EXTRA_MESSAGE, message.notification?.body)
        message.data.forEach {
            intent.putExtra(it.key, it.value)
        }

        sendBroadcast(intent)
    }

    companion object {
        const val INTENT_FILTER = "FIREBASE_MESSAGE_EVENT"

        const val EXTRA_TITLE = "TITLE"
        const val EXTRA_MESSAGE = "MESSAGE"
        const val EXTRA_DATA = "DATA"
    }

}