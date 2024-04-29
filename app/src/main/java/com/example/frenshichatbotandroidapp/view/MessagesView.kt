package com.example.frenshichatbotandroidapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenshichatbotandroidapp.R
import com.example.frenshichatbotandroidapp.ui.theme.FrenShiColors
/**!
 * Composable function to create the frenshi View
 */
@Composable
fun FrenShiView(frenShiResponseText: String, frenShiResponseDate: String) {
    Column(
        Modifier
            .padding(top=10.dp)
            .fillMaxSize()) {
        Text(text = frenShiResponseDate, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Row(modifier = Modifier.padding(start = 60.dp)) {
                    Image(painter = painterResource(id = R.drawable.frenshi), contentDescription = "f", modifier = Modifier.drawBehind {
                        drawCircle(
                            color = FrenShiColors.Black,
                            radius = this.size.maxDimension
                        )
                    }
                    )
                Text(
                    text = frenShiResponseText,
                    color = FrenShiColors.White,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(start = 30.dp, end = 25.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(FrenShiColors.Black)
                        .wrapContentSize()
                        .padding(9.dp)
                )
            }
    }
}
/**!
 * Composable function to create the user input view
 */
@Composable
fun UserInputView(userMsgTextRetrieved: String, userMsgDateRetrieved: String) {
    Column(
        Modifier
            .padding(start = 100.dp, end = 25.dp)
            .fillMaxSize()) {
            Text(text = userMsgDateRetrieved, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Text(text = userMsgTextRetrieved, color = FrenShiColors.White,
                textAlign = TextAlign.Left, modifier = Modifier
                    .padding(end = 25.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .background(FrenShiColors.Red)
                    .wrapContentSize()
                    .padding(9.dp))
    }
}