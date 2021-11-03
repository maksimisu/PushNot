package com.maksimisu.pushnot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val title = intent?.getStringExtra(MyFirebaseMessagingService.EXTRA_TITLE)
                val message = intent?.getStringExtra(MyFirebaseMessagingService.EXTRA_MESSAGE)
                val data = intent?.getStringExtra(MyFirebaseMessagingService.EXTRA_DATA)
                if(title != null && message != null) {
                    showAlertDialog(title, message, data)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Notification item cannot be null!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(MyFirebaseMessagingService.INTENT_FILTER)

        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    private fun showAlertDialog(title: String, message: String, data: String?) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Show data") { _, _ ->
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(MyFirebaseMessagingService.EXTRA_DATA, data)
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}