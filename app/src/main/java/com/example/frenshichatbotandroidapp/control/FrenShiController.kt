package com.example.frenshichatbotandroidapp.control

import com.example.frenshichatbotandroidapp.data.FrenShiEN
import com.example.frenshichatbotandroidapp.data.FrenShiFR

class FrenshiFRController(val frenShiFR: FrenShiFR) {
    fun onFrenShiInit(){
        frenShiFR.initPrediction()
    }
    fun onFrenShiPredict(userInputText: String){
        frenShiFR.predictResponse(userInputText)
    }

}
class FrenshiENController(val frenShiEN: FrenShiEN) {
    fun onFrenshiInit(){
        frenShiEN.initPrediction()
    }
    fun onFrenShiPredict(userInputText: String){
        frenShiEN.predictResponse(userInputText)
    }
}