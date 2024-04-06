package com.example.frenshichatbotandroidapp.view

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.frenshichatbotandroidapp.data.Message
import com.example.frenshichatbotandroidapp.data.MessagesList
import com.example.frenshichatbotandroidapp.ui.theme.FrenshiColors

/**!
 * brief composable function to create the app GUI
 */
@SuppressLint("MutableCollectionMutableState")
@Composable
fun AppGUIView(messagesListModelView: MessagesList, messagesController: MessagesController, orientation: Int) {
    //a state to store the changes user input
    var userMsgText by rememberSaveable{
        mutableStateOf("Posez-moi une question..")
    }

    val messagesListItems = messagesListModelView.messagesList //a state to store the change of the list of messages in the chat using Model View architecture
    // a state to store the changes of user input length
    var userMsgTextLength by rememberSaveable {
        mutableIntStateOf(0)
    }
    //Select the fraction for lazyColumn based on the mobile orientation
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
            items(messagesListItems?: mutableListOf<Message>()){
                    UserInputView(messagesController.onUserInputTextRetrieveContent(it), messagesController.onUserInputDateRetrieveContent(it)) //render the user input using the inputed data from user
                    FrenshiView() //render Frenshi answer using the retrieved data from the backend dataset
            }
        }
        Column (Modifier.fillMaxSize()){
            TextField(value = userMsgText,
                onValueChange =
                {
                    userMsgText = it //update the user input GUI
                    userMsgTextLength = userMsgText.length //get the length of the user input
                }
                , modifier = Modifier.fillMaxWidth())
            Row {
                Text(text = "$userMsgTextLength/2000", color = FrenshiColors.Gray, textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(0.9F))
                IconButton(onClick =
                {
                    messagesController.onUserInputTextAdd(userMsgText) //add user input to the list of messages each time the button is clicked
                    userMsgText = "Posez-moi une question.."
                }
                ) {
                    Icon(Icons.Filled.Send, contentDescription = "")
                }
            }
        }
    }
}