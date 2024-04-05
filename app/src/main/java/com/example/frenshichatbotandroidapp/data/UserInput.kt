package com.example.frenshichatbotandroidapp.data

import java.util.Date

/**!
@brief a class to hold user input data
 */
class UserInputText() {
        private var inputMsg: CharSequence = ""
        private var inputMsgLength = 0
        private var msgSendTime = Date()
        fun setUserInputText(input: CharSequence){this.inputMsg = input}
        fun setUserInputTextLength(inputLength: Int){this.inputMsgLength = inputLength}
        fun setUserInputTextSendTime(msgSendTime: Date){this.msgSendTime = msgSendTime}
        fun getUserInputText(): CharSequence {return this.inputMsg}
        fun getUserInputTextLength():Int{return this.inputMsgLength}
        fun getUserInputTextSendTime(): Date{return msgSendTime}
}