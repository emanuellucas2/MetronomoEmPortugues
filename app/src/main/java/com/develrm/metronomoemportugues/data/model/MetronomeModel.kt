package com.develrm.metronomoemportugues.data.model

import com.develrm.metronomoemportugues.data.model.enum.BeatsEnum
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum

data class MetronomeModel(
    val bpm: Int,
    val beats: BeatsEnum,
    val subdivision: SubdivisionEnum
    )

