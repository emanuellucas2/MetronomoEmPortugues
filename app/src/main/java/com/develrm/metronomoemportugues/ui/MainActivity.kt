package com.develrm.metronomoemportugues.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.develrm.metronomoemportugues.ui.theme.MetronomoEmPortuguesTheme
import com.develrm.metronomoemportugues.viewmodel.MetronomeViewModel


class MainActivity : ComponentActivity() {

    val viewModel: MetronomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetronomoEmPortuguesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()) {
                        Lights()
                        Slider(viewModel)
                        Buttons()
                    }
                }
            }
        }
    }
}

@Composable
fun Slider(viewModel: MetronomeViewModel) {
    val bpmValue = remember { mutableStateOf(0f) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = "BPM: ${bpmValue.value.toInt()}",
             color = Color.White,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        Slider(
            value = bpmValue.value,
            onValueChange = { newValue ->
                bpmValue.value = newValue
            },
            valueRange = 20f..120f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Lights() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Circle()
        Circle()
        Circle()
        Circle()
    }
}

@Composable
fun Circle() {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color.Red, shape = CircleShape)
    )
}


@Composable
fun Buttons() {

    Column(modifier = Modifier
        .fillMaxWidth()) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){

            Button(onClick = {  },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RectangleShape) {
                Text(text = "PLAY", fontSize = 32.sp)
            }
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){

            Button(onClick = {  },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RectangleShape) {
                Text(text = "3/4", fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {  },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RectangleShape) {
                Text(text = "1", fontSize = 32.sp)
            }
        }
    }


}
