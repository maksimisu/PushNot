package com.maksimisu.pushnot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tv = findViewById<TextView>(R.id.tvData)
        tv.text = intent?.getStringExtra(MyFirebaseMessagingService.EXTRA_DATA)
    }
}