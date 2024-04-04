package com.example.frenshichatbotandroidapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.recyclerview.widget.RecyclerView
import com.example.frenshichatbotandroidapp.Model.FrenshiEN
import com.example.frenshichatbotandroidapp.Model.UserInput


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{AppGUIView()}
        /*var chat = ""
        var user = UserInput()
        val frenshiChatbotEN = FrenshiEN()
        var chattingMsgs = findViewById<RecyclerView>(R.id.chat)
        val tags = listOf("GreetingDayEN", "GreetingEveningEN", "GoodbyeDayEN", "GoodByeEveningEN")
        val sendButton = findViewById<Button>(R.id.sendButton)
        //set user input and predict answer everytime the send button is clicked
        sendButton.setOnClickListener {
            //user.setUserInput(findViewById<TextView>(R.id.userInput).text)
            var index = (1..3).random()
            chat += user.getUserInput()
            chat += "\n"
            var tag = tags[index]
            var ChatBotanswer = frenshiChatbotEN.getDataset(tag)
            frenshiChatbotEN.setAnswer(ChatBotanswer)
            chat += ChatBotanswer
            chat += "\n"
            //chattingMsgs.
        }*/

    }
}
