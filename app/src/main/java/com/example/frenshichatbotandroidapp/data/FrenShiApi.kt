package com.example.frenshichatbotandroidapp.data
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import java.util.UUID

/**!
 *  A data class implementing an interface to hold a list of FrenShi database records
 */
@Serializable
data class FrenShiDataNetwork(
    @SerialName("intents")
    val frenShiRecordsNetwork : MutableList<FrenShiRecordNetwork>
)
/**!
 *  A Data class implementing an interface to hold and manage a FrenShi database record
 */
@Serializable
data class FrenShiRecordNetwork(
    @SerialName("tag")
    val tag: String,
    @SerialName("input")
    val inputs: MutableList<String>,
    @SerialName("responses")
    val responses:MutableList<String>
) {
    fun toFrenShiResponsesRoom()= FrenShiresponsesRoom(UUID.randomUUID().toString(), tag, responses[(0..1).random()])
    //fun toFrenShiInputsRoom()= FrenShiInputsRoom(UUID.randomUUID().toString(), tag, inputs[0])
}