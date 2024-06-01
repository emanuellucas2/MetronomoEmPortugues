package com.develrm.metronomoemportugues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develrm.metronomoemportugues.data.model.MetronomeModel
import com.develrm.metronomoemportugues.data.model.StateModel
import com.develrm.metronomoemportugues.data.model.enum.BeatsEnum
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum
import com.develrm.metronomoemportugues.data.repository.MetronomeRepository
import com.develrm.metronomoemportugues.util.MediaUtil
import com.develrm.metronomoemportugues.util.calculateInterval
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetronomeViewModel @Inject constructor(private val repository: MetronomeRepository, private val mediaUtil: MediaUtil) : ViewModel() {

    private val _metronome = MutableStateFlow(MetronomeModel())
    val metronome: StateFlow<MetronomeModel> = _metronome

    private val _state = MutableStateFlow(StateModel())
    val state: StateFlow<StateModel> = _state

    private var metronomeJob: Job? = null
    private var tickCount = 0
    init {
        _metronome.value = repository.getMetronome()
    }

    fun toggleMetronome(){
        val isExecuting = _state.value.isExecuting
        _state.value = state.value.copy(isExecuting = !isExecuting, beat = -1)

        if(!isExecuting){
            metronomeJob = viewModelScope.launch {
                while (true) {
                    _state.value = state.value.copy(subdivisionBeat = ++tickCount % _metronome.value.subdivision.value)

                    mediaUtil.emitVoiceSound(_metronome.value.subdivision,
                                             _metronome.value.beats.value,
                                             _state.value.beat,
                                             _state.value.subdivisionBeat)


                    if (_state.value.subdivisionBeat == 0) {
                        updateBeat()
                        mediaUtil.emitTickSound()
                    }
                    delay(calculateInterval(_metronome.value.bpm,_metronome.value.subdivision.value))
                }
            }
        }else{
            mediaUtil.stopVoiceSound()
            mediaUtil.stopTickSound()
            metronomeJob?.cancel()
        }
    }

    private fun updateBeat() {
        _state.value = _state.value.copy(beat = ((_state.value.beat + 1) % _metronome.value.beats.value))
    }

    private fun saveMetronome() {
        viewModelScope.launch {
            repository.saveMetronome(_metronome.value)
        }
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
}