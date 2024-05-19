package com.develrm.metronomoemportugues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develrm.metronomoemportugues.data.model.MetronomeModel
import com.develrm.metronomoemportugues.data.model.enum.BeatsEnum
import com.develrm.metronomoemportugues.data.model.enum.SubdivisionEnum
import com.develrm.metronomoemportugues.data.repository.MetronomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MetronomeViewModel @Inject constructor(private val repository: MetronomeRepository) : ViewModel() {

    private val _metronome = MutableStateFlow<MetronomeModel>(MetronomeModel(60,
                                                                                BeatsEnum.FOUR,
                                                                                SubdivisionEnum.ONE
                                                                            )
                                                             )
    val metronome: StateFlow<MetronomeModel> = _metronome

    init {
        viewModelScope.launch {
            _metronome.value = repository.getMetronome()
        }
    }

    fun updateBpm(newBpm: Int) {
        _metronome.value = _metronome.value.copy(bpm = newBpm)
        saveMetronome()
    }

    fun updateBeats(newBeats: BeatsEnum) {
        _metronome.value = _metronome.value.copy(beats = newBeats)
    }

    fun updateSubdivision(newSubdivision: SubdivisionEnum) {
        _metronome.value = _metronome.value.copy(subdivision = newSubdivision)
    }


    private fun saveMetronome() {
        viewModelScope.launch {
            repository.saveMetronome(_metronome.value)
        }
    }
}