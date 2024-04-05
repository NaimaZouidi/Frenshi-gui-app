package com.example.frenshichatbotandroidapp.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.frenshichatbotandroidapp.control.*
import com.example.frenshichatbotandroidapp.ui.theme.FrenshiColors

/**!
 * brief composable function to create the app GUI
 */
@Composable
fun AppGUIView(userInputTextController: UserInputTextController, orientation: Int) {
    var userMsgText by rememberSaveable{
        mutableStateOf("Posez-moi une question..")
    }
    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    var userMsgTextLength by rememberSaveable {
        mutableIntStateOf(0)
    }
    val lazyColumnFraction = if(orientation == Configuration.ORIENTATION_LANDSCAPE){
        0.75F
    }
    else{
        0.85F
    }
    Column {
        LazyColumn (
            Modifier
                .fillMaxHeight(lazyColumnFraction)
                .padding(top = 20.dp, bottom = 20.dp)){
            items(count) {
                    UserInputView(userInputTextController)
                    //FrenshiView()
            }
        }
        Column (Modifier.fillMaxSize()){
            TextField(value = userMsgText,
                onValueChange =
                {
                    userMsgText = it
                    userMsgTextLength = userInputTextController.onUserTextTyping(userMsgText)
                }
                , modifier = Modifier.fillMaxWidth())
            Row {
                Text(text = "$userMsgTextLength/2000", color = FrenshiColors.Gray, textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(0.9F))
                IconButton(onClick =
                {
                    count++
                    userInputTextController.onUserInputTextAdd(userMsgText)
                    userMsgText = "Posez-moi une question.."
                }
                ) {
                    Icon(Icons.Filled.Send, contentDescription = "")
                }
            }
        }
    }
}