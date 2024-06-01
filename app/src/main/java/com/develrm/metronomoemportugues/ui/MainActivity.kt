package com.develrm.metronomoemportugues.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.develrm.metronomoemportugues.R
import com.develrm.metronomoemportugues.ui.theme.MetronomoEmPortuguesTheme
import com.develrm.metronomoemportugues.viewmodel.MetronomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                        Slider()
                        Buttons()
                    }
                }
            }
        }
    }
}

@Composable
fun Slider() {
    val viewModel: MetronomeViewModel = hiltViewModel()
    val metronome by viewModel.metronome.collectAsState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = "BPM: ${metronome.bpm}",
             color = Color.White,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())

        Slider(
            value = metronome.bpm.toFloat(),
            onValueChange = { newValue ->
                viewModel.updateBpm(newValue.toInt())
            },
            valueRange = 20f..120f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Lights() {
    val viewModel: MetronomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val metronome by viewModel.metronome.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in 0 until metronome.beats.value) {
            Circle(i == state.beat && state.isExecuting)
        }
    }
}

@Composable
fun Circle( isRed: Boolean) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(if (isRed) Color.Red else MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
    )
}


@Composable
fun Buttons() {
    val viewModel: MetronomeViewModel = hiltViewModel()
    val metronome by viewModel.metronome.collectAsState()
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){

            Button(onClick = { viewModel.toggleMetronome() },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RectangleShape) {
                IconActionButton(
                    iconResourceId = if(state.isExecuting) R.drawable.stop else R.drawable.play
                ) {
                    viewModel.toggleMetronome()
                }
            }
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly){

            Button(onClick = {
                                viewModel.updateBeats(metronome.beats.next())
                            },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RectangleShape) {
                Text(text = metronome.beats.bar, fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                                 viewModel.updateSubdivision(metronome.subdivision.next())
                             },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RectangleShape) {
                IconActionButton(
                    iconResourceId = metronome.subdivision.iconId
                ) {
                    viewModel.updateSubdivision(metronome.subdivision.next())
                }
            }
        }
    }
}

@Composable
fun IconActionButton(
    iconResourceId: Int,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(48.dp)
    ) {
        // Load the icon using painterResource
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = null
        )
    }
}
