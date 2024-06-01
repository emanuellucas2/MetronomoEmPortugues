package com.develrm.metronomoemportugues.util

import com.develrm.metronomoemportugues.R
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum

fun calculateInterval(bpm : Int, subdivision: Int): Long {
    return (1000 / ((bpm * subdivision ) / 60.0)).toLong()
}
fun chooseSound(subdivision : SubdivisionEnum,beats: Int, tick: Int): Int {
    return when (subdivision) {
        SubdivisionEnum.ONE -> beatSound(beats,tick)
        SubdivisionEnum.TWO -> if (tick % 2 == 0) beatSound(beats,tick) else R.raw.i
        SubdivisionEnum.FOUR -> when (tick % 4) {
            0 -> beatSound(beats,tick)
            1 -> R.raw.i
            2 -> R.raw.e
            3 -> R.raw.a
            else -> 0
        }
    }
}
fun beatSound(beats : Int, tick: Int): Int {
    return when((tick+1) % beats){
        0 ->  R.raw.one
        1 -> R.raw.two
        2 -> R.raw.three
        3 -> R.raw.four
        4 -> R.raw.five
        5 -> R.raw.six
        else -> 0
    }
}