package com.example.frenshichatbotandroidapp.model
/**!
 * @brief an interface for creating Frenshi chatbot
 */
interface Frenshi {

    fun setDataset()
    fun getDataset(tag: String): String?
    fun setAnswer(answer: String?)
    fun getAnswer(): String
    fun predictAnswer(userInput: String) : String
}