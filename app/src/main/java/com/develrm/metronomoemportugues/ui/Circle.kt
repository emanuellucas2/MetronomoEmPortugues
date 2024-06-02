package com.develrm.metronomoemportugues.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Circle(isTurnOn: Boolean) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(if (isTurnOn) Color.Red else MaterialTheme.colorScheme.onPrimary, shape = CircleShape)
    )
}
