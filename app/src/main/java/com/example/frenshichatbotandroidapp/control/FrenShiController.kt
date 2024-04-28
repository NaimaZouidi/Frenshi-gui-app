package com.example.frenshichatbotandroidapp.control

import com.example.frenshichatbotandroidapp.data.FrenShi

class FrenShiController(val frenShi: FrenShi) {
    fun onFrenShiInit(){
        frenShi.initPrediction()
    }
    fun onFrenShiPredict(userInputText: String){
        frenShi.predict(userInputText)
    }

}
/*class FrenshiENController(val frenShiEN: FrenShiEN) {
    fun onFrenshiInit(){
        frenShiEN.initPrediction()
    }
    fun onFrenShiPredict(userInputText: String){
        frenShiEN.predictResponse(userInputText)
    }
}*/