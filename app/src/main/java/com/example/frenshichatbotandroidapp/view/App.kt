package com.example.frenshichatbotandroidapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.frenshichatbotandroidapp.R
import com.example.frenshichatbotandroidapp.ui.theme.FrenshiColors

@Composable
fun AppGUIView(){
    Column {
        LazyColumn (Modifier.fillMaxSize(0.9F)){
            item(100) {
                /*To be defined*/
            }
        }
        TextField(value = stringResource(id = R.string.user_input_text), onValueChange = {}, modifier = Modifier.fillMaxWidth())
        Row {
            Text(text = stringResource(id = R.string.caractere_number_default), color = FrenshiColors.Gray, textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(0.9F))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Send, contentDescription = "")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AppGUIViewPreview(){
    AppGUIView()
}