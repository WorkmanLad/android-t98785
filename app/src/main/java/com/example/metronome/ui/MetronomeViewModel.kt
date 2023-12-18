package com.example.metronome.ui

import androidx.lifecycle.ViewModel
import com.example.metronome.data.MetronomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MetronomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MetronomeUiState())
    val uiState: StateFlow<MetronomeUiState> = _uiState.asStateFlow()

    private var ms: Duration = (60_000 / 120).toDuration(DurationUnit.MILLISECONDS)

    fun onPlayButton() {
        _uiState.update { currentState ->
            currentState.copy(playing = !_uiState.value.playing)
        }
    }

    suspend fun start() {
        while (_uiState.value.playing) {
            delay(ms)

            val nextActiveCircle = if (_uiState.value.activeCircle + 1 > 3) {
                0
            } else {
                _uiState.value.activeCircle + 1
            }

            _uiState.update { currentState ->
                currentState.copy(activeCircle = nextActiveCircle)
            }
        }
    }

    fun onMinusButton() {
        _uiState.update { currentState ->
            currentState.copy(bpm = _uiState.value.bpm - 1)
        }
        ms = (60_000 / _uiState.value.bpm).toDuration(DurationUnit.MILLISECONDS)
    }

    fun onPlusButton() {
        _uiState.update { currentState ->
            currentState.copy(bpm = _uiState.value.bpm + 1)
        }
        ms = (60_000 / _uiState.value.bpm).toDuration(DurationUnit.MILLISECONDS)
    }
}