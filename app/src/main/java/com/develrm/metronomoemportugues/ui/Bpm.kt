package com.develrm.metronomoemportugues.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.develrm.metronomoemportugues.viewmodel.MetronomeViewModel

@Composable
fun Bpm() {
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