package com.develrm.metronomoemportugues.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.develrm.metronomoemportugues.viewmodel.MetronomeViewModel

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