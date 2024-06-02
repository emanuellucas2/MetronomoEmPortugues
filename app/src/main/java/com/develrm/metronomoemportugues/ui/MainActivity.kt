package com.develrm.metronomoemportugues.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.develrm.metronomoemportugues.ui.theme.MetronomoEmPortuguesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetronomoEmPortuguesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.weight(0.3f),
                            contentAlignment = Alignment.Center){
                            Lights()
                        }
                        Box(modifier = Modifier.weight(0.2f),
                            contentAlignment = Alignment.Center){
                            Bpm()
                        }
                        Box(modifier = Modifier.weight(0.5f),
                            contentAlignment = Alignment.Center){
                            Buttons()
                        }
                    }
                }
            }
        }
    }
}
