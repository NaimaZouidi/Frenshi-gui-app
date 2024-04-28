package com.example.frenshichatbotandroidapp.data

import org.tensorflow.lite.Interpreter

/**!
 * An interface for implementing FrenShi chatbots
 */
interface FrenShi {
    fun initPrediction()
    suspend fun loadModel(): Interpreter?
    suspend fun loadVocab() : HashMap<String,Int>?
    fun convertToLowercaseAndRemovePunctuation(userInputText : String):String
    fun tokenizeUserInput(userInputText : String) : IntArray
    fun padSequence(sequence : IntArray) : IntArray
    fun predictResponse(userInputText: String) : Float
}