package com.example.frenshichatbotandroidapp.control

import com.example.frenshichatbotandroidapp.data.FrenShi
/**!
 * A class implementing a controller for FrenShi chatbot
 */
class FrenShiController(val frenShi: FrenShi, val messagesController: MessagesController) {
    fun onFrenShiInit(){
        frenShi.initPrediction()
    }
    fun onFrenShiPredict(userInputText: String) {
        frenShi.predict(userInputText) {
            messagesController.onMessageAdd(
                "FrenShi",
                it
            ) //predict anwser to the user input and add it to the list of messages each time the button is clicked
        }
    }
}