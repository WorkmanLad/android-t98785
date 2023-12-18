package com.example.metronome.data

data class MetronomeUiState(
    val bpm: Int = 120,
    val playing: Boolean = false,
    val activeCircle: Int = 0
)
