package com.example.moviesapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.moviesapp.R
import com.example.moviesapp.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({ init() }, 1500)
    }
    private fun init() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}