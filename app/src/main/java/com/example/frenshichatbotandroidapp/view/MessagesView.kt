package com.example.frenshichatbotandroidapp.view

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenshichatbotandroidapp.R
import com.example.frenshichatbotandroidapp.ui.theme.FrenshiColors
/**!
 * brief composable function to create the frenshi View
 */
@Composable
fun FrenshiView(){
    Column(
        Modifier
            .padding(5.dp)
            .fillMaxSize()) {
        Text(text = stringResource(id = R.string.default_time), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Row(modifier = Modifier.padding(start = 60.dp)) {
                Text(
                    text = stringResource(id = R.string.frenshi), fontSize = 20.sp,
                    color = FrenshiColors.White, modifier = Modifier.drawBehind {
                        drawCircle(
                            color = FrenshiColors.Gray,
                            radius = this.size.maxDimension
                        )
                    }
                )
                Text(
                    text = stringResource(id = R.string.empty),
                    color = FrenshiColors.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 30.dp, end = 20.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(FrenshiColors.Gray)
                        .wrapContentSize()
                )
            }
    }
}
/**!
 * brief composable function to create the user input view
 */
@Composable
fun UserInputView(userMsgTextRetrieved: String, userMsgDateRetrieved: String) {
    Column(
        Modifier
            .padding(start = 60.dp, end = 5.dp)
            .fillMaxSize()) {
            Text(text = userMsgDateRetrieved, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Text(text = userMsgTextRetrieved, color = FrenshiColors.White,
                textAlign = TextAlign.Justify, modifier = Modifier
                    .padding(end = 20.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .background(FrenshiColors.Blue)
                    .wrapContentSize())
    }
}