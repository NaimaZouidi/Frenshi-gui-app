package com.example.frenshichatbotandroidapp.control

import com.example.frenshichatbotandroidapp.data.*
import java.text.SimpleDateFormat
import java.util.Calendar

/**!
 * brief class that implements the controller for the list of message
 */
class MessagesController(private var messagesList: MessagesList) {
        fun onUserInputTextAdd(userMsgText: String){
            val c = Calendar.getInstance()
            val date = c.time
            val sdf = SimpleDateFormat("HH:mm")
            val formatedDate = sdf.format(date)
            messagesList.addMessageToList(userMsgText, formatedDate)
        }
        fun onUserInputDateRetrieveContent(message: Message?): String{
            return message?.getMessageSendTime()?: ""
        }
        fun onUserInputTextRetrieveContent(message: Message?):String{
            return message?.getMessageText()?: ""
        }

}