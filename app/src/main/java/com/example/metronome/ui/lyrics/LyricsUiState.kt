package com.example.metronome.ui.lyrics

import com.example.metronome.model.Song

data class LyricsUiState(
    val currentSong: Song = Song("", "", "", mapOf()),
    val playTitleAnimation: Boolean = false
)
