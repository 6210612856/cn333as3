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
import com.example.cn333.ui.theme.Cn333Theme
import kotlin.random.Random.Default.nextInt
import androidx.appcompat.app.AlertDialog as AlertDialog1

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Interface()
        }
    }

}


@Composable
fun Interface(){

        val textState = remember { mutableStateOf(TextFieldValue()) }
        var textresult = remember { mutableStateOf("Guess your number")}
        var count = 0
        var random : Int = nextInt(1,1000)


        fun checkAns() {
            var ans = if (textState.value.text == null){0} else {textState.value.text.toInt()}
            if (ans > random){
                count++
                textresult.value = "Lower number than yours"
            }
            else if (ans < random){
                count++
                textresult.value = "Higher number than yours"
            }
            else{
                count++
                alertWinning(count)
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


@Composable
fun alertWinning(count : Int){
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = {})
            { Text(text = "You have tried times") }
        },
    )
}



@Preview
@Composable
fun Previewer(){
    Interface()
}
