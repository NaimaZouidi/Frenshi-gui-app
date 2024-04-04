package com.example.frenshichatbotandroidapp.model
/**!
@brief a class holding user input data
 */
class UserInput() {
        private var inputMsg: CharSequence = ""
        private var inputMsgLength = 0
        private var msgSendTime = "00:00"
        fun setUserInput(input: CharSequence){this.inputMsg = input}
        fun setUserInputLength(inputLength: Int){this.inputMsgLength = inputLength}
        fun setUserInputSendTime(msgSendTime : String){this.msgSendTime = msgSendTime}
        fun getUserInput(): CharSequence {return this.inputMsg}
        fun getUserInputLength():Int{return this.inputMsgLength}
        fun getUserInputSendTime(): String{return msgSendTime}
}