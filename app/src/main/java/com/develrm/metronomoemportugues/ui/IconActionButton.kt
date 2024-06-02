package com.develrm.metronomoemportugues.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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