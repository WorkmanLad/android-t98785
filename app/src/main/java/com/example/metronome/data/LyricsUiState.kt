package com.example.metronome.data

import com.example.metronome.model.Song

data class LyricsUiState(
    val currentSong: Song = Song("", "", "", mapOf()),
)
