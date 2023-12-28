package com.example.metronome.model

data class Song(
    val title: String,
    val artist: String,
    val lyrics: String,
    val notes: Map<Int, String>
)
