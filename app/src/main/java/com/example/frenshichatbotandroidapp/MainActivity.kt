package com.example.frenshichatbotandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.frenshichatbotandroidapp.control.FrenShiController
import com.example.frenshichatbotandroidapp.control.MessagesController
import com.example.frenshichatbotandroidapp.data.FrenShiDataNetwork
import com.example.frenshichatbotandroidapp.data.FrenShi
import com.example.frenshichatbotandroidapp.data.FrenShiRecordNetwork
import com.example.frenshichatbotandroidapp.data.FrenShiDatabase
import com.example.frenshichatbotandroidapp.data.MessagesList
import com.example.frenshichatbotandroidapp.view.AppGUIView
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    } //prepare http client to retrieve data from the database json file
    private val database by lazy{
        Room.databaseBuilder(applicationContext, FrenShiDatabase::class.java, "database")
    .build() //build a room database for FrenShi
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val userLanguage = resources.configuration.locale.language
        val frenShi = if( userLanguage == "fr")
             FrenShi(this, "frenShi-fr.tflite", "word-dict-fr.json", "FR", database)
        else{
            FrenShi(this, "frenShi-en.tflite", "word-dict-en.json", "EN", database)
        }
        val messagesListModelView : MessagesList by viewModels() //instantiate the Model View for the messages
        val messagesController = MessagesController(messagesListModelView) //instantiate the messages controller
        val frenShiController = FrenShiController(frenShi, messagesController)
        super.onCreate(savedInstanceState)
        setContent{AppGUIView(messagesListModelView, messagesController, frenShiController)} //call the view composable function to render the GUI
        frenShiController.onFrenShiInit()
        lifecycleScope.launch(Dispatchers.IO) {
            val frenShiENRecordsNetwork = fetchFrenShiDatabasesFromServer(userLanguage)
            saveFrenshiDataRecordsToDatabase(frenShiENRecordsNetwork)
        }
    }
    /**!
    * A suspend function to retrieve training datasets (FR and EN versions) from the remote server
    * @return a pair of the retrieved data from the remote server (FR and EN versions)
    */
    private suspend fun fetchFrenShiDatabasesFromServer(userLanguage: String): List<FrenShiRecordNetwork> {
        val response : FrenShiDataNetwork = if(userLanguage == "fr"){ //based on the user local download dataset
            httpClient.get("https://raw.githubusercontent.com/NaimaZouidi/Frenshi-gui-app/main/dataset/dataset-fr.json")
                .body<FrenShiDataNetwork>()
        }
        else {
            httpClient.get("https://raw.githubusercontent.com/NaimaZouidi/Frenshi-gui-app/main/dataset/dataset-en.json")
                .body<FrenShiDataNetwork>()
        }
        return response.frenShiRecordsNetwork
    }

    /**!
     * A function to save the parsed datasets in Room databases
     * @param frenShiDataRecords the retrieved data from the remote server
     */
    private fun saveFrenshiDataRecordsToDatabase(frenShiDataRecords: List<FrenShiRecordNetwork>) {
        val frenShiResponsesRoom = frenShiDataRecords.map { it.toFrenShiResponsesRoom()} //convert retrieved data to Room database records
        //val FrenShiInputsRoom = frenShiDataRecords.map { it.toFrenShiInputsRoom()}
        database.FrenShiDataDao().insertAllResponses(*frenShiResponsesRoom.toTypedArray()) //insert the retrieved data to the Room database
    }
}
