package com.example.frenshichatbotandroidapp

class FrenshiFR() : Frenshi{
        private val datasetFR = mapOf("GreetingFR" to "Salut! Je m'appelle Frenshi", "GoodbyeDayFR" to "Au revoir")
        private var answerFR : String = ""
        //ifstream* datasetFRFile
        //override fun setDataset(){}
        override fun getDataset(tag: String): String? {return datasetFR[tag]}
        override fun setAnswer(answer : String?){this.answerFR = answer?:""}
        override fun getAnswer():String{return this.answerFR}
        //string predictAnswer(string userInput);
}