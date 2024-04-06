package com.example.frenshichatbotandroidapp.data
/**!
 * brief a class that implements the EN version of Frenshi chatbot
 */
class FrenshiEN() : Frenshi {
        private val datasetEN = mapOf("" to "") //to be added
        private var answerEN : String = ""
        override fun setDataset(){/*To be added*/}
        override fun getDataset(tag: String): String? {return datasetEN[tag]}
        override fun setAnswer(answer : String?){answerEN = answer?:""}
        override fun getAnswer() : String{return answerEN}
        override fun predictAnswer(userInput: String) : String {return "To be added"}
}