package com.develrm.metronomoemportugues.data.model.enum

enum class BeatsEnum(val value: Int, val bar: String) {
    TWO(2,"2/4"),
    THREE(3,"3/4"),
    FOUR(4,"4/4"),
    SIX(6,"6/8");
    fun next(): BeatsEnum {
        val values = values()
        return values[(this.ordinal + 1) % values.size]
    }
}