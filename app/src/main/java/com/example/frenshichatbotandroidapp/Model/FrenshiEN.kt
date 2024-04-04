package com.example.frenshichatbotandroidapp.Model

/**!
 * brief a class to create Frenshi chatbot EN version
 */
class FrenshiEN() : Frenshi {
        private val datasetEN = mapOf("GreetingDayEN" to "Hi! My name is Frenshi", "GreetingEveningEN" to "Good evening! My name is Frenshi!", "GoodbyeDayEN" to "Goodbye", "GoodbyeEveningEN" to "Good night")
        private var answerEN : String = "" //attributes should alway be initialized
        override fun setDataset(){/*To be added*/}
        override fun getDataset(tag: String): String? {return datasetEN[tag]}
        override fun setAnswer(answer : String?){answerEN = answer?:""}
        override fun getAnswer() : String{return answerEN}
        override fun predictAnswer(userInput: String) : String {return "To be added"}
}