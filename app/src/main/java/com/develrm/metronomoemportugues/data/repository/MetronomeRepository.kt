package com.develrm.metronomoemportugues.data.repository

import android.content.Context
import com.develrm.metronomoemportugues.data.model.MetronomeModel
import com.develrm.metronomoemportugues.data.model.enum.BeatsEnum
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Singleton
class MetronomeRepository(@ApplicationContext context: Context) {
    private val sharedPreferences = context.getSharedPreferences("metronome_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveMetronome(model: MetronomeModel) {
        val json = gson.toJson(model)
        sharedPreferences.edit().putString(KEY_METRONOME, json).apply()
    }

    fun getMetronome(): MetronomeModel {
        val json = sharedPreferences.getString(KEY_METRONOME, null)
        return if (json != null) {
            gson.fromJson(json, MetronomeModel::class.java)
        } else {
            MetronomeModel(
                bpm = 60,
                beats = BeatsEnum.FOUR.value,
                subdivision = SubdivisionEnum.ONE.value
            )
        }
    }

    companion object {
        private const val KEY_METRONOME = "key_metronome"
    }
}