package com.example.frenshichatbotandroidapp
class FrenshiEN() : Frenshi {
        private val datasetEN = mapOf("GreetingDayEN" to "Hi! My name is Frenshi", "GreetingEveningEN" to "Good evening! My name is Frenshi!", "GoodbyeDayEN" to "Goodbye", "GoodbyeEveningEN" to "Good night")
        private var answerEN : String = "" //attributes should alway be initialized
        //ifstream* datasetENFile
        //override fun setDataset(){}
        override fun getDataset(tag: String): String? {return datasetEN[tag]}
        override fun setAnswer(answer : String?){answerEN = answer?:""}
        override fun getAnswer() : String{return answerEN}
        //string predictAnswer(string userInput);
}