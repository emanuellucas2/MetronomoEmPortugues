package com.develrm.metronomoemportugues.data.model.enum

import com.develrm.metronomoemportugues.R

enum class SubdivisionEnum(val value: Int,val iconId: Int) {
    ONE(1, R.drawable.one),
    TWO(2, R.drawable.two),
    FOUR(4, R.drawable.four);

    fun next(): SubdivisionEnum {
        val values = SubdivisionEnum.values()
        return values[(this.ordinal + 1) % values.size]
    }

}