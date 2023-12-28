package com.example.metronome.data

import com.example.metronome.model.Song

object TestSong {
    val song = Song(
        title = "Once in a moon",
        artist = "Imagine Dragons",
        lyrics = "You're the face of the future,\nthe blood in my veins.",
        notes = mapOf(
            Pair(0, "Am"),
            Pair(15, "D"),
            Pair(30, "G"),
            Pair(51, "Em"),
        )
    )
}