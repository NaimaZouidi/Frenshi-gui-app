package com.example.frenshichatbotandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frenshichatbotandroidapp.ui.theme.FrenshiColors

@Composable
fun FrenshiView(){
    Column(Modifier.padding(5.dp).height(60.dp)) {
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
                    modifier = Modifier.padding(start = 30.dp, top = 5.dp, bottom = 5.dp, end = 20.dp)
                        .clip(RoundedCornerShape(7.dp)).background(FrenshiColors.Gray).fillMaxWidth()
                )
            }
    }
}
@Composable
fun UserInputView(){
    Column(Modifier.padding(start = 60.dp, end = 5.dp).fillMaxWidth().height(60.dp)) {
            Text(text = stringResource(id = R.string.default_time), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Text(text = stringResource(id = R.string.empty), color = FrenshiColors.White,
                textAlign = TextAlign.Justify, modifier = Modifier.padding(top=5.dp, bottom = 5.dp, end=20.dp).clip(RoundedCornerShape(7.dp)).background(FrenshiColors.Blue).fillMaxWidth())
    }
}
@Preview(showBackground = true)
@Composable
fun FrenshiViewPreview(){
    FrenshiView()
}
@Preview(showBackground = true)
@Composable
fun UserInputViewPreview(){
    UserInputView()
}