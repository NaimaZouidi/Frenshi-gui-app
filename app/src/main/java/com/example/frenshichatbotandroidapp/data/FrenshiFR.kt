package com.example.frenshichatbotandroidapp.data

/**!
 * brief a class to create Frenshi chatbot FR version
 */
class FrenshiFR() : Frenshi {
        private val datasetFR = mapOf("GreetingFR" to "Salut! Je m'appelle Frenshi", "GoodbyeDayFR" to "Au revoir")
        private var answerFR : String = ""
        override fun setDataset(){/*To be added*/}
        override fun getDataset(tag: String): String? {return datasetFR[tag]}
        override fun setAnswer(answer : String?){this.answerFR = answer?:""}
        override fun getAnswer():String{return this.answerFR}
        override fun predictAnswer(userInput: String) : String {return "To be added"}
}