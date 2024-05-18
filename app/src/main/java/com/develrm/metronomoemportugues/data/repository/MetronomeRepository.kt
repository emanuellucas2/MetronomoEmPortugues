package com.develrm.metronomoemportugues.data.repository

import android.content.Context
import com.develrm.metronomoemportugues.data.model.MetronomeModel
import com.google.gson.Gson

class MetronomeRepository(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("metronome_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveMetronome(model: MetronomeModel) {
        val json = gson.toJson(model)
        sharedPreferences.edit().putString(KEY_METRONOME, json).apply()
    }

    fun getMetronome(): MetronomeModel {
        val json = sharedPreferences.getString(KEY_METRONOME, null)
        return gson.fromJson(json, MetronomeModel::class.java)
    }

    companion object {
        private const val KEY_METRONOME = "key_metronome"
    }
}