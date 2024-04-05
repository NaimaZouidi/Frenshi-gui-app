package com.example.frenshichatbotandroidapp.data
/**!
 * @brief an interface for creating Frenshi chatbots
 */
interface Frenshi {

    fun setDataset()
    fun getDataset(tag: String): String?
    fun setAnswer(answer: String?)
    fun getAnswer(): String
    fun predictAnswer(userInput: String) : String
}