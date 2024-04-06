package com.example.frenshichatbotandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.frenshichatbotandroidapp.control.*
import com.example.frenshichatbotandroidapp.data.MessagesList
import com.example.frenshichatbotandroidapp.view.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val messagesListModelView : MessagesList by viewModels() //instantiate the Model View for the messages
        val messagesController = MessagesController(messagesListModelView) //instantiate the messages controller
        setContent{ AppGUIView(messagesListModelView, messagesController, resources.configuration.orientation)} //call the view composable function to render the GUI
    }
}
