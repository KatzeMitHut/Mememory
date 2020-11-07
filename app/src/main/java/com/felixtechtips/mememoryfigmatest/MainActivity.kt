package com.felixtechtips.mememoryfigmatest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onePlayer.setOnClickListener {
            startActivity(Intent(this, OnePlayer::class.java))
            finish()
        }
    }
}