package com.example.metronome.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LyricsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val lyricsSize = 20
        val notes: Array<Boolean?> = arrayOfNulls(lyricsSize)
        
        NotesBar(notes = notes)
    }
}

@Composable
fun LyricsLine(line: String) {

}

@Composable
fun NotesBar(notes: Array<Boolean?>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        for (note in notes) {
            val color = if (note == true) Color.Green else Color.Transparent
            Note(color = color);
        }
    }
}

@Composable
fun Note(color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .width(5.dp)
            .height(7.dp),
        colors = CardDefaults.cardColors(color)
    ) {

    }
}