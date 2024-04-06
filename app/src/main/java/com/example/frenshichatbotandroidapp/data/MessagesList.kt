package com.example.frenshichatbotandroidapp.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

/**!
 * @brief a class that implements a list of message
 */
class MessagesList : ViewModel(){
    val messagesList = mutableStateListOf<Message>()
    fun addMessageToList(messageText: String, messageSendTime: String){
        messagesList.add(Message(messageText, messageSendTime))
    }
}