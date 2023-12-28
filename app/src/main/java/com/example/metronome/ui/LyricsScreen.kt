package com.example.metronome.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metronome.data.TestSong
import com.example.metronome.model.Song
import com.example.metronome.ui.theme.MetronomeTheme

@Composable
fun LyricsScreen(song: Song, modifier: Modifier = Modifier, viewModel: LyricsViewModel = viewModel()) {
    Column(
        modifier = modifier
    ) {
        val uiState = viewModel.uiState.collectAsState()

        viewModel.setSong(song)
        val notesLines = viewModel.getNotesLine()
        val lyricsLines = viewModel.getLyricsLines()
        val linesIterator = viewModel.iteratorValue

        SongTitle(
            viewModel.titleForDisplay,
            uiState.value.currentSong.artist,
            Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        var y = 0
        while (y < linesIterator) {
            NotesLine(line = notesLines[y])
            LyricsLine(line = lyricsLines[y])
            ++y
        }
    }
}

@Composable
private fun SongTitle(title: String, artist: String, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState(0)
    LaunchedEffect(Unit) {
        while (true) {
            scrollState.animateScrollTo(
                (scrollState.maxValue / 2) + 355,
                animationSpec = tween(durationMillis = 10000)
            )
            scrollState.scrollTo(0)
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displayLarge,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            modifier = Modifier.fillMaxWidth(.5f).horizontalScroll(scrollState)
        )
        Text(text = artist, style = MaterialTheme.typography.displaySmall)
    }
}

@Composable
private fun LyricsLine(line: String) {
    Text(text = line, style = MaterialTheme.typography.bodyLarge)
}

@Composable
private fun NotesLine(line: String) {
    Text(text = line, style = MaterialTheme.typography.labelLarge, color = Color(0.2f, 0.8f, 0.68f))
}

@Preview
@Composable
private fun Preview() {
    MetronomeTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LyricsScreen(song = TestSong.song,modifier = Modifier.fillMaxSize().padding(30.dp))
        }
    }
}