package com.example.frenshichatbotandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.frenshichatbotandroidapp.control.FrenshiFRController
import com.example.frenshichatbotandroidapp.control.MessagesController
import com.example.frenshichatbotandroidapp.data.FrenShiDataNetwork
import com.example.frenshichatbotandroidapp.data.FrenShiENDatabase
import com.example.frenshichatbotandroidapp.data.FrenShiFR
import com.example.frenshichatbotandroidapp.data.FrenShiRecordNetwork
import com.example.frenshichatbotandroidapp.data.FrenshiFRDatabase
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
    private val databaseEN by lazy{
        Room.databaseBuilder(applicationContext, FrenShiENDatabase::class.java, "database-en")
            .build()
        //build a room database for FrenShi EN version
    }
    private val databaseFR by lazy{
        Room.databaseBuilder(applicationContext, FrenshiFRDatabase::class.java, "database-fr")
    .build() //build a room database for FrenShi FR version
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val frenShiFR = FrenShiFR(this, "model-fr.tflite", "word-dict-fr.json")
        val messagesListModelView : MessagesList by viewModels() //instantiate the Model View for the messages
        val messagesController = MessagesController(messagesListModelView) //instantiate the messages controller
        val frenShiFRController = FrenshiFRController(frenShiFR)
        super.onCreate(savedInstanceState)
        setContent{AppGUIView(messagesListModelView, messagesController, frenShiFRController)} //call the view composable function to render the GUI
        frenShiFRController.onFrenShiInit()
        lifecycleScope.launch(Dispatchers.IO) {
            val (frenShiFRDataNetwork, frenShiENRecordsNetwork) = fetchFrenShiDatabasesFromServer()
            saveFrenshiDataRecordsToDatabase(frenShiFRDataNetwork, frenShiENRecordsNetwork)
        }
    }
    /**!
    * A suspend function to retrieve training datasets (FR and EN versions) from the remote server
    * @return a pair of the retrieved data from the remote server (FR and EN versions)
    */
    private suspend fun fetchFrenShiDatabasesFromServer(): Pair<List<FrenShiRecordNetwork>, List<FrenShiRecordNetwork>> {
        val responseFR = httpClient.get("https://raw.githubusercontent.com/NaimaZouidi/Frenshi-gui-app/main/Datasets/dataset-fr.json")
                .body<FrenShiDataNetwork>()
        val responseEN : FrenShiDataNetwork = httpClient.get("https://raw.githubusercontent.com/NaimaZouidi/Frenshi-gui-app/main/Datasets/dataset-en.json")
            .body<FrenShiDataNetwork>()
        return Pair(responseFR.frenShiRecordsNetwork, responseEN.frenShiRecordsNetwork)
    }

    /**!
     * A function to save the parsed datasets in Room databases
     * @param frenShiFRDataRecords the retrieved data from the remote server (FR version)
     * @param frenShiENDataRecords the retrieved data from the remote server (EN version)
     */
    private fun saveFrenshiDataRecordsToDatabase(frenShiFRDataRecords: List<FrenShiRecordNetwork>, frenShiENDataRecords: List<FrenShiRecordNetwork>) {
        val frenShiFRResponsesRoom = frenShiFRDataRecords.map { it.toFrenShiFRResponsesRoom()} //convert retrieved data to Room database records
        val frenShiENResponsesRoom = frenShiENDataRecords.map { it.toFrenShiENResponsesRoom()}
        //val FrenShiFRInputsRoom = frenShiFRDataRecords.map { it.toFrenShiFRInputsRoom()}
        //val FrenShiENInputsRoom = frenShiENDataRecords.map { it.toFrenShiENInputsRoom()}
        databaseFR.FrenshiFRDataDao().insertAllResponses(*frenShiFRResponsesRoom.toTypedArray()) //insert the retrieved data to the Room database
        databaseEN.FrenShiENDataDao().insertAllResponses(*frenShiENResponsesRoom.toTypedArray())
    }
}
