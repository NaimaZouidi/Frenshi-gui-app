package com.example.frenshichatbotandroidapp
/**!
 * @brief an abstract class for frenshi chatbot
 */
interface Frenshi {

    //fun setDataset()
    fun getDataset(tag: String): String?
    fun setAnswer(answer: String?)
    fun getAnswer(): String
    //virtual string predictAnswer(string userInput)=0
}