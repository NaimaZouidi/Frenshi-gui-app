package com.example.frenshichatbotandroidapp.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

/**!
 * A class implementing a list of message
 */
class MessagesList : ViewModel(){
    val messagesList = mutableStateListOf<Message>()
    fun addMessageToList(messageSender: String, messageText: String, messageSendTime: String){
        messagesList.add(Message(messageSender, messageText, messageSendTime))
    }
}