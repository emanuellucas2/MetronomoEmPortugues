package com.develrm.metronomoemportugues.data.model

import com.develrm.metronomoemportugues.data.model.enum.BeatsEnum
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum

data class StateModel (
    val isExecuting: Boolean = false,
    val beat: Int = -1,
    val subdivisionBeat: Int = 1
)