package com.develrm.metronomoemportugues.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develrm.metronomoemportugues.R
import com.develrm.metronomoemportugues.data.model.MetronomeModel
import com.develrm.metronomoemportugues.data.model.enum.BeatsEnum
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum
import com.develrm.metronomoemportugues.data.repository.MetronomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetronomeViewModel @Inject constructor(private val repository: MetronomeRepository,@ApplicationContext private val context: Context) : ViewModel() {
    private var metronomeJob: Job? = null
    private val _metronome = MutableStateFlow(MetronomeModel())

    val metronome: StateFlow<MetronomeModel> = _metronome

    private var mediaPlayer: MediaPlayer? = null

    init {
        _metronome.value = repository.getMetronome()
        _metronome.value.copy(isExecuting = false, redCircle = -1)
    }

    private fun calculateInterval(): Long {
        val bpm = _metronome.value.bpm
        val subdivision = _metronome.value.subdivision.value
        return (1000 / ((bpm * subdivision ) / 60.0)).toLong()
    }

    private fun emitSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.tick)
            mediaPlayer?.setOnCompletionListener {
                stopSound()
            }
        }
        mediaPlayer?.start()
    }

    private fun stopSound() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun updateBpm(newBpm: Int) {
        _metronome.value = _metronome.value.copy(bpm = newBpm)
        saveMetronome()
    }

    fun updateBeats(newBeats: BeatsEnum) {
        _metronome.value = _metronome.value.copy(beats = newBeats)
        saveMetronome()
    }

    fun updateSubdivision(newSubdivision: SubdivisionEnum) {
        _metronome.value = _metronome.value.copy(subdivision = newSubdivision)
        saveMetronome()
    }

    fun toggleMetronome(){
        val isExecuting = _metronome.value.isExecuting
        _metronome.value = _metronome.value.copy(isExecuting = !isExecuting, redCircle = -1)

        if(!isExecuting){
            var tickCount = 0
            metronomeJob = viewModelScope.launch {
                while (true) {
                    emitSound()
                    tickCount++
                    if (tickCount % _metronome.value.subdivision.value == 0) {
                        updateRedCircle()
                    }
                    delay(calculateInterval())
                }
            }
        }else{
            stopSound()
            metronomeJob?.cancel()
        }
    }

    private fun updateRedCircle() {
        _metronome.value = _metronome.value.copy(redCircle = ((_metronome.value.redCircle + 1) % _metronome.value.beats.value))
    }


    private fun saveMetronome() {
        viewModelScope.launch {
            repository.saveMetronome(_metronome.value)
        }
    }
}