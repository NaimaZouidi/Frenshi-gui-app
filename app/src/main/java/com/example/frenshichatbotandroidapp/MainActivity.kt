package com.example.frenshichatbotandroidapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var chat = ""
        var user = UserInput()
        val frenshiChatbotEN = FrenshiEN()
        var chattingMsgs = findViewById<TextView>(R.id.chat)
        val tags = listOf("GreetingDayEN", "GreetingEveningEN", "GoodbyeDayEN", "GoodByeEveningEN")
        val sendButton = findViewById<Button>(R.id.sendButton)
        //set user input and predict answer everytime the send button is clicked
        sendButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?)
            {
                user.setUserInput(findViewById<TextView>(R.id.userInput).text)
                var index = (1..3).random()
                chat += user.getUserInput()
                chat += "\n"
                var tag = tags[index]
                var ChatBotanswer = frenshiChatbotEN.getDataset(tag)
                frenshiChatbotEN.setAnswer(ChatBotanswer)
                chat += ChatBotanswer
                chat += "\n"
                chattingMsgs.text = chat
            }
        })
    }
}
