package com.example.frenshichatbotandroidapp.data

/**!
@brief a class that implements a message
 */
class Message(private val msgText: String,
              private val msgSendTime: String){
        fun getMessageText(): String {return msgText}
        fun getMessageSendTime(): String{return msgSendTime}
}