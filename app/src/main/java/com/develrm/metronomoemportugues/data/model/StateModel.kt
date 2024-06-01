package com.develrm.metronomoemportugues.data.model

data class StateModel (
    val isExecuting: Boolean = false,
    val beat: Int = -1,
    val subdivisionBeat: Int = 1
)