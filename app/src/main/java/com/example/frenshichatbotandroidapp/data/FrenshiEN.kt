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
class FrenShiEN(private val context: Context,
                private val modelAssetsName: String,
                private val vocabAssetsName: String) : FrenShi {
        private lateinit var vocabData : HashMap<String,Int>
        private lateinit var tfLiteInterpreter : Interpreter
        private val outputLength = 45
        private val inputShape = 18
        override fun initPrediction() {
                        CoroutineScope( Dispatchers.Default ).launch {
                                val interpreter = loadModel()
                                val vocab = loadVocab()
                                if( vocab != null && interpreter != null ) {
                                        this@FrenShiEN.vocabData = vocab
                                        this@FrenShiEN.tfLiteInterpreter = interpreter
                                        /*withContext( Dispatchers.Main ) {
                                            onComplete()
                                        }*/
                                }
                                else {
                                        throw Exception( "Could not load model" )
                                }
                        }
                }
        override fun tokenizeUserInput(userInputText: String) : IntArray {
                return userInputText
                        .split(" " )
                        .map { it.trim() }
                        .filter { it.isNotEmpty() }
                        .map { part -> vocabData[part] ?: 0 }
                        .toIntArray()
        }
        override fun padSequence(sequence : IntArray) : IntArray {
                val paddedSequence = IntArray( inputShape ){ 0 }
                sequence.forEachIndexed { i, part->
                        paddedSequence[inputShape-(sequence.size)+i] = part
                }
                return paddedSequence
        }
        override fun predictResponse(userInputText: String) : Float {
                CoroutineScope( Dispatchers.Default ).launch {
                        val inputs : Array<FloatArray> = arrayOf(
                                padSequence( tokenizeUserInput( userInputText) )
                                        .map{ it.toFloat() }
                                        .toFloatArray()
                        )
                        // Output shape -> ( 1 , 45 ) ( as numClasses = 45 )
                        val outputs : Array<FloatArray> = arrayOf( FloatArray( outputLength ) )
                        tfLiteInterpreter.run( inputs , outputs )

                }
                return 0.0F
        }
        override fun convertToLowercaseAndRemovePunctuation(userInputText: String): String {
                return userInputText.lowercase().replace("\\p{Punct}".toRegex(), "")
        }
        override suspend fun loadVocab(): HashMap<String,Int>? = withContext( Dispatchers.IO ) {
                Log.d( "Model" , "Loading vocab from $vocabAssetsName" )
                val inputStream = context.assets?.open( vocabAssetsName )
                if( inputStream != null ) {
                        val reader = BufferedReader( InputStreamReader( inputStream ) )
                        val jsonContents = reader.readText()
                        val jsonObject = JSONObject( jsonContents )
                        val iterator: Iterator<String> = jsonObject.keys()
                        val data = HashMap<String, Int>()
                        while (iterator.hasNext()) {
                                val key = iterator.next()
                                val index = jsonObject.get( key )
                                if( index is Int ) {
                                        data[ key ] = index.toInt()
                                }
                        }
                        return@withContext data
                }
                else { null }
        }
        override suspend fun loadModel(): Interpreter? = withContext( Dispatchers.IO ) {
                Log.d( "Model" , "Loading model from $modelAssetsName" )
                return@withContext try {
                        Interpreter( FileUtil.loadMappedFile(context, modelAssetsName) )
                }
                catch (e: IOException) {
                        e.printStackTrace()
                        null
                }
        }
}