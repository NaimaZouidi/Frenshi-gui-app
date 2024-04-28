package com.example.frenshichatbotandroidapp.data

import org.tensorflow.lite.Interpreter

/**!
 * An interface for implementing ML/DL models
 */
interface MachineLearningModel {
    fun initPrediction()
    suspend fun loadModel(): Interpreter?
    fun predict(userInputText: String)
}