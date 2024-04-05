package com.example.frenshichatbotandroidapp.control

import com.example.frenshichatbotandroidapp.data.*
import java.util.Calendar

/**!
 * brief class to implement the controller for any user input text
 */
class UserInputTextController(private val userInputText: UserInputText) {
        fun onUserTextTyping(userMsgText: String) : Int{
            this.userInputText.setUserInputTextLength((userMsgText.length))
            return userInputText.getUserInputTextLength()
        }
        fun onUserInputTextAdd(userMsgText: String){
            this.userInputText.setUserInputText(userMsgText)
            val date = Calendar.getInstance().time
            this.userInputText.setUserInputTextSendTime(date)
        }
        fun onUserInputDateRetrieveContent(): String{
        return this.userInputText.getUserInputTextSendTime().toString()
        }
        fun onUserInputTextRetrieveContent():String{
            return this.userInputText.getUserInputText().toString()
        }

}