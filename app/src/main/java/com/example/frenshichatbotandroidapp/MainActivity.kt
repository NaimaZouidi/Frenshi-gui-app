package com.example.frenshichatbotandroidapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.frenshichatbotandroidapp.view.frenShiWelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userLanguage = resources.configuration.locale.language
        setContent {frenShiWelcomeScreen(userLanguage)} //call the view composable function to render the GUI
        Handler().postDelayed(Runnable {
            val navigate = Intent(this, ChatActivity::class.java)
            startActivity(navigate)
        }, 1200)
    }
}