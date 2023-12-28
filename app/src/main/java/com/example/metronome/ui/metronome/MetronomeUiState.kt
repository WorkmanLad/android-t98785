package com.example.metronome.ui.metronome

data class MetronomeUiState(
    val bpm: Int = 120,
    val playing: Boolean = false,
    val activeCircle: Int = 0
)
