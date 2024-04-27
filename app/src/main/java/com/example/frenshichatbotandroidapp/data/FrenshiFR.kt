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
 * A class implementing the FR version of FrenShi chatbot
 */
class FrenShiFR(
        private val context: Context,
        private val modelAssetsNameFR: String,
        private val vocabAssetsNameFR: String
        ) : FrenShi {
                private lateinit var vocabDataFR: HashMap<String, Int>
                private lateinit var tfLiteInterpreterFR: Interpreter
                private val outputLength = 46
                private val inputShape = 18
                override fun initPrediction() {
                        CoroutineScope(Dispatchers.Default).launch {
                                val interpreter = loadModel()
                                val vocab = loadVocab()
                                if (vocab != null && interpreter != null) {
                                        this@FrenShiFR.vocabDataFR = vocab
                                        this@FrenShiFR.tfLiteInterpreterFR = interpreter
                                        /*withContext( Dispatchers.Main ) {
                                            onComplete()
                                        }*/
                                } else {
                                        throw Exception("Could not load model")
                                }
                        }
                }

                override fun tokenizeUserInput(userInputText: String): IntArray {
                        return userInputText
                                .split(" ")
                                .map { it.trim() }
                                .filter { it.isNotEmpty() }
                                .map { part -> vocabDataFR[part] ?: 0 }
                                .toIntArray()
                }

                override fun padSequence(sequence: IntArray): IntArray {
                        val paddedSequence = IntArray(inputShape) { 0 }
                        sequence.forEachIndexed { i, part->
                                paddedSequence[inputShape-(sequence.size)+i] = part
                        }
                        return paddedSequence
                }
                override fun convertToLowercaseAndRemovePunctuation(userInputText: String): String {
                        val prep = userInputText.lowercase().replace("\\p{Punct}".toRegex(), "")
                        Log.d("Text preprocessing", "Processed text data $prep")
                        return prep
                }
                override fun predictResponse(userInputText: String): Float {
                        CoroutineScope(Dispatchers.Default).launch {
                                val inputs: Array<FloatArray> = arrayOf(
                                        padSequence(tokenizeUserInput(convertToLowercaseAndRemovePunctuation(userInputText)))
                                                .map { it.toFloat() }
                                                .toFloatArray()
                                )
                                //Output shape -> ( 1 , 46 ) ( as numClasses = 46 )
                                val outputs: Array<FloatArray> = arrayOf(FloatArray(outputLength))
                                //tfLiteInterpreterFR.run(inputs, outputs) //run model prediction

                        }
                        return 0.0F
                }

                override suspend fun loadVocab(): HashMap<String, Int>? =
                        withContext(Dispatchers.IO) {
                                Log.d("Model", "Loading vocab from $vocabAssetsNameFR")
                                val inputStream = context.assets?.open(vocabAssetsNameFR)
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
                        Log.d("Model", "Loading model from $modelAssetsNameFR")
                        return@withContext try {
                                Interpreter(FileUtil.loadMappedFile(context, modelAssetsNameFR))
                        } catch (e: IOException) {
                                e.printStackTrace()
                                null
                        }
                }
}
