package com.example.frenshichatbotandroidapp.view

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.frenshichatbotandroidapp.R
import com.example.frenshichatbotandroidapp.control.FrenShiController
import com.example.frenshichatbotandroidapp.control.MessagesController
import com.example.frenshichatbotandroidapp.data.MessagesList
import kotlinx.coroutines.launch


/**!
 * Composable function to create the app GUI
 */
@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("MutableCollectionMutableState")
@Composable
fun AppGUIView(
    messagesListModelView: MessagesList,
    messagesController: MessagesController,
    frenShiController: FrenShiController,
) {
    //a state to store the changes user input
    var userMsgText by rememberSaveable{
        mutableStateOf("")
    }
    //a state to store the changes of the send button
    var enableSendButton by rememberSaveable {
        mutableStateOf(false)
    }
    val lazyColumnListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val messagesListItems = messagesListModelView.messagesList //a state to store the change of the list of messages in the chat using Model View architecture
    Column (){
        LazyColumn(
            Modifier
                .weight(1f)
                .padding(bottom = 20.dp),
            state = lazyColumnListState,
            verticalArrangement = Arrangement.Bottom) {
            coroutineScope.launch { //always scroll to the last item in the messages list
                lazyColumnListState.animateScrollToItem(messagesListItems.size)
            }
            item {
                FrenShiView(
                    stringResource(id = R.string.greeting_frenShiEN),
                    messagesController.onCreateDate()
                ) //render Frenshi answer using the retrieved data from the backend dataset
            }
            items(messagesListItems) {
                if (messagesController.onMessageRetrieveSender(it) == "user")
                    UserInputView(
                        messagesController.onMessageRetrieveContent(it),
                        messagesController.onMessageRetrieveDate(it)
                    ) //render the user input using the inputted data from user
                else
                    FrenShiView(
                        messagesController.onMessageRetrieveContent(it),
                        messagesController.onMessageRetrieveDate(it)
                    ) //render Frenshi answer using the retrieved data from the backend dataset
            }
        }
        Row (Modifier.padding(bottom=40.dp)){
            TextField(
                value = userMsgText,
                onValueChange =
                {
                    userMsgText = it //update the user input GUI
                    enableSendButton = true
                },
                placeholder = {
                    Text(stringResource(id = R.string.default_user_inputEN))
                },
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .padding(start = 25.dp)
                    .clip(RoundedCornerShape(7.dp))
            )
            IconButton(enabled = enableSendButton,
                onClick = {
                    messagesController.onMessageAdd(
                        "user",
                        userMsgText
                    ) //add user input to the list of messages each time the button is clicked
                    frenShiController.onFrenShiPredict(userMsgText)
                    userMsgText = ""
                    enableSendButton = false
                }
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = stringResource(id = R.string.Button_content_description_EN)
                )
            }
        }
    }
}
@Composable
fun frenShiWelcomeScreen(userLanguage: String) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        val gifFilename = if(userLanguage == "fr") {
             R.drawable.frenshi_welcome_screen_fr
        }
        else {
            R.drawable.frenshi_welcome_screen_en
        }
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = gifFilename).apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}