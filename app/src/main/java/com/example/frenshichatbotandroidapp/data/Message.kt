package com.example.frenshichatbotandroidapp.data

/**!
 * A class implementing a message
 */
class Message(private val msgSender : String,
              private val msgText: String,
              private val msgSendTime: String){
        fun getMessageSender(): String {return msgSender}
        fun getMessageText(): String {return msgText}
        fun getMessageSendTime(): String{return msgSendTime}
}