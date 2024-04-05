package com.example.frenshichatbotandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.frenshichatbotandroidapp.control.*
import com.example.frenshichatbotandroidapp.data.*
import com.example.frenshichatbotandroidapp.view.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var userInputText = UserInputText()
        //var frenshiChatbotEN = FrenshiEN()
        //var frenshiChatbotEN = FrenshiFR()
        val userInputController = UserInputTextController(userInputText)
        setContent{ AppGUIView(userInputController, resources.configuration.orientation)}

    }
}
