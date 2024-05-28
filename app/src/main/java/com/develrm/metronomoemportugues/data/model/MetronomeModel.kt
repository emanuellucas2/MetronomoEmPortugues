package com.develrm.metronomoemportugues.data.model

import com.develrm.metronomoemportugues.data.model.enum.BeatsEnum
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum

data class MetronomeModel(
    val bpm: Int = 60,
    val beats: BeatsEnum = BeatsEnum.FOUR,
    val subdivision: SubdivisionEnum = SubdivisionEnum.ONE,
    val isExecuting: Boolean = false,
    val redCircle: Int = -1
    )

