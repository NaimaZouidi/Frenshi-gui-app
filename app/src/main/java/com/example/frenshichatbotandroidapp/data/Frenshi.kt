package com.example.frenshichatbotandroidapp.data

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**!
 * A class implementing the EN version of FrenShi chatbot
 */
//46, 18
class FrenShi(
        private val context: Context,
        private val modelAssetsName: String,
        private val vocabAssetsName: String,
        private val versionFrenshi: String,
        private val database: FrenShiDatabase
) : MachineLearningModel {
                private lateinit var vocabData: HashMap<String, Int>
                private lateinit var tfLiteInterpreter: Interpreter
                private lateinit var answer : String
                private val tags : List<String> = if(versionFrenshi == "EN"){
                        listOf("APL EN",
                                "APL calculation EN",
                                "APL requirements EN",
                                "Apply for Supplementary health insurance EN",
                                "Apply for health insurance EN",
                                "Apply for health insurance card EN",
                                "Apply for resident permit EN",
                                "Apply to APL EN",
                                "CAF EN",
                                "Civil state EN",
                                "Civil state health insurance EN",
                                "Civil state resident permit EN",
                                "Documents for Resident permit EN",
                                "Download Ameli app EN",
                                "E-photo EN",
                                "Employee EN",
                                "First APL payment EN",
                                "First stage health insurance EN",
                                "GoodbyeDay EN",
                                "GoodbyeEvening EN",
                                "Health insurance EN",
                                "Health insurance account EN",
                                "Health insurance card EN",
                                "Hosting agreement EN",
                                "IBAN EN",
                                "Identity photograph EN",
                                "Preferred doctor EN",
                                "Proof of address EN",
                                "RIB EN",
                                "Reason for staying EN",
                                "Resident permit EN",
                                "Second stage health insurance EN",
                                "Student holding a scholarship EN",
                                "Student renting a flat EN",
                                "Student sponsored by a third party EN",
                                "Student staying at a hotel EN",
                                "Student staying with family or relatives EN",
                                "Student with sufficient financial ressources EN",
                                "Supplementary health insurance EN",
                                "Supporting documents APL EN",
                                "Supporting documents Financial ressources EN",
                                "Supporting documents health insurance EN",
                                "Third stage health insurance EN",
                                "Unknown EN",
                                "Update health insurance card EN",
                                "Validate visa EN")
                }
                else{
                        listOf("APL EN",
                                "APL calculation EN",
                                "APL requirements EN",
                                "Apply for Supplementary health insurance EN",
                                "Apply for health insurance EN",
                                "Apply for health insurance card EN",
                                "Apply for resident permit EN",
                                "Apply to APL EN",
                                "CAF EN",
                                "Civil state EN",
                                "Civil state health insurance EN",
                                "Civil state resident permit EN",
                                "Documents for Resident permit EN",
                                "Download Ameli app EN",
                                "E-photo EN",
                                "Employee EN",
                                "First APL payment EN",
                                "First stage health insurance EN",
                                "GoodbyeDay EN",
                                "GoodbyeEvening EN",
                                "Health insurance EN",
                                "Health insurance account EN",
                                "Health insurance card EN",
                                "Hosting agreement EN",
                                "IBAN EN",
                                "Identity photograph EN",
                                "Preferred doctor EN",
                                "Proof of address EN",
                                "Bank details EN",
                                "Reason for staying EN",
                                "Resident permit EN",
                                "Second stage health insurance EN",
                                "Student holding a scholarship EN",
                                "Student renting a flat EN",
                                "Student sponsored by a third party EN",
                                "Student staying at a hotel EN",
                                "Student staying with family or relatives EN",
                                "Student with sufficient financial resources EN",
                                "Supplementary health insurance EN",
                                "Supporting documents APL EN",
                                "Supporting documents Financial resources EN",
                                "Supporting documents health insurance EN",
                                "Third stage health insurance EN",
                                "Unknown EN",
                                "Update health insurance card EN",
                                "Validate visa EN")
                }
                companion object{
                        private const val OUTPUT_LENGTH = 46
                        private const val INPUT_SHAPE = 18
                }
                private fun setAnswer(answerDatabase: String){
                        this@FrenShi.answer= answerDatabase
                }
                fun getOutputLength(): Int{
                        return OUTPUT_LENGTH
                }
                fun getInputShape(): Int{
                        return INPUT_SHAPE
                }

                fun getAnswer(): String{
                        return answer
                }
                private fun queryAnswer(answerTag: String) {
                        val answerDatabase = database.FrenShiDataDao().getResponse(answerTag)
                        Log.d("Answer from database", "$answerDatabase")
                        setAnswer(answerDatabase)
                }
                override fun initPrediction() {
                        CoroutineScope(Dispatchers.Default).launch {
                                val interpreter = loadModel() //load the NLP model
                                val vocab = loadVocab() //load the word index dictionary
                                if (vocab != null && interpreter != null) {
                                        this@FrenShi.vocabData = vocab
                                        this@FrenShi.tfLiteInterpreter = interpreter
                                } else {
                                        throw Exception("Could not load model")
                                }
                        }
                }
                override fun predict(userInputText: String, onComplete: ((String) -> Unit)){
                        CoroutineScope(Dispatchers.Default).launch {
                                val inputs: Array<FloatArray> = arrayOf(
                                        padSequence(tokenizeUserInput(convertToLowercaseAndRemovePunctuation(userInputText)))
                                                .map { it.toFloat() }
                                                .toFloatArray()
                                )//preprocessing user input
                                //Output shape -> ( 1 , 46 ) ( as numClasses = 46 )
                                val outputs: Array<FloatArray> = arrayOf(FloatArray(OUTPUT_LENGTH))
                                tfLiteInterpreter.run(inputs, outputs) //run model inference
                                val answerTagIndex = argMax(outputs[0])
                                val answerTag = tags[answerTagIndex]
                                queryAnswer(answerTag)
                                onComplete(answer)
                        }
                }
                private fun padSequence(sequence: IntArray): IntArray {
                        val paddedSequence = IntArray(INPUT_SHAPE) { 0 }
                        sequence.forEachIndexed { i, part->
                                paddedSequence[INPUT_SHAPE-(sequence.size)+i] = part
                        }
                        return paddedSequence
                }
                private fun convertToLowercaseAndRemovePunctuation(userInputText: String): String {
                        val prep = userInputText.lowercase().replace("\\p{Punct}".toRegex(), "")
                        Log.d("Text preprocessing", "Processed text data $prep")
                        return prep
                }
                private fun tokenizeUserInput(userInputText: String): IntArray {
                        return userInputText
                                .split(" ")
                                .map { it.trim() }
                                .filter { it.isNotEmpty() }
                                .map { part -> vocabData[part] ?: 0 }
                                .toIntArray()
                }
                private suspend fun loadVocab(): HashMap<String, Int>? =
                        withContext(Dispatchers.IO) {
                                Log.d("Model", "Loading vocab from $vocabAssetsName")
                                val inputStream = context.assets?.open(vocabAssetsName)
                                if (inputStream != null) {
                                        val reader = BufferedReader(InputStreamReader(inputStream))
                                        val jsonContents = reader.readText()
                                        val jsonObject = JSONObject(jsonContents)
                                        val iterator: Iterator<String> = jsonObject.keys()
                                        val data = HashMap<String, Int>()
                                        while (iterator.hasNext()) {
                                                val key = iterator.next()
                                                val index = jsonObject.get(key)
                                                if (index is Int) {
                                                        data[key] = index.toInt()
                                                }
                                        }
                                        return@withContext data
                                } else {
                                        null
                                }
                        }
                override suspend fun loadModel(): Interpreter? = withContext(Dispatchers.IO) {
                        Log.d("Model", "Loading model from $modelAssetsName")
                        return@withContext try {
                                Interpreter(FileUtil.loadMappedFile(context, modelAssetsName)) //create tensorflow lite interpreter
                        } catch (e: IOException) {
                                e.printStackTrace()
                                null
                        }
                }
                fun argMax(outputs: FloatArray): Int{
                        val maxProb = outputs.maxOrNull()
                        outputs.forEachIndexed{i, prob ->
                                if(prob == maxProb){
                                        return i
                                }
                        }
                        return -1
                }
}
