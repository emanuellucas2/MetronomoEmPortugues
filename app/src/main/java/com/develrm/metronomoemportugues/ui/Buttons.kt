package com.develrm.metronomoemportugues.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.develrm.metronomoemportugues.R
import com.develrm.metronomoemportugues.viewmodel.MetronomeViewModel

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
                shape = RectangleShape
            ) {
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
                shape = RectangleShape
            ) {
                Text(text = metronome.beats.bar, fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                viewModel.updateSubdivision(metronome.subdivision.next())
            },
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                shape = RectangleShape
            ) {
                IconActionButton(
                    iconResourceId = metronome.subdivision.iconId
                ) {
                    viewModel.updateSubdivision(metronome.subdivision.next())
                }
            }
        }
    }
}