package com.example.frenshichatbotandroidapp

class UserInput() {
        private var input: CharSequence = ""
        private var inputLength = 0
        fun setUserInput(input: CharSequence){this.input = input}
        fun setUserInputLength(inputLength: Int){this.inputLength = inputLength}
        fun getUserInput(): CharSequence {return this.input}
        fun getUserInputLength():Int{return this.inputLength}
}