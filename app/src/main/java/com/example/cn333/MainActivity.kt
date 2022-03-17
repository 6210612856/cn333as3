package com.example.cn333

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random.Default.nextInt

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Interface()
        }
    }

}

var random : Int = nextInt(1,1000)
var count = 0

@Composable
fun Interface(){

        val textState = remember { mutableStateOf(TextFieldValue()) }
        var textresult = remember { mutableStateOf("Guess your number")}
        var textscore = remember { mutableStateOf("")}





        fun checkAns() {
            var ans = if (textState.value.text == null){0} else {textState.value.text.toInt()}
            if (ans > random){
                count++
                textscore.value = ""
                textresult.value = "Lower number than yours"
            }
            else if (ans < random){
                count++
                textscore.value = ""
                textresult.value = "Higher number than yours"
            }
            else{
                count++
                textscore.value = "You have tried $count times"
                count = 0
                textresult.value = "Let's do it again"
                random = nextInt( 1, 1000)

            }
        }




        Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

            ){
        Text(text = textscore.value)

        TextField(value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .padding(20.dp)
                .size(100.dp, 50.dp),

        )

        Button(onClick = {checkAns()}) {
            Text(
                text = textresult.value,
            )
        }

    }

}



@Preview
@Composable
fun Previewer(){
    Interface()
}
