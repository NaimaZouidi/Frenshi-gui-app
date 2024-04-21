package com.example.frenshichatbotandroidapp.control

import com.example.frenshichatbotandroidapp.data.*
import java.text.SimpleDateFormat
import java.util.Calendar

/**!
 * A class implementing a controller for the list of messages
 */
class MessagesController(private var messagesList: MessagesList) {
        fun onMessageAdd(messageSender: String, messageText: String){
            val c = Calendar.getInstance()
            val date = c.time
            val sdf = SimpleDateFormat("HH:mm")
            val formatedDate = sdf.format(date)
            messagesList.addMessageToList(messageSender, messageText, formatedDate)
        }
        fun onMessageRetrieveSender(message: Message?):String{
            return message?.getMessageSender()?:""
        }
        fun onMessageRetrieveContent(message: Message?):String{
            return message?.getMessageText()?: ""
        }
        fun onMessageRetrieveDate(message: Message?): String{
        return message?.getMessageSendTime()?: ""
        }

}