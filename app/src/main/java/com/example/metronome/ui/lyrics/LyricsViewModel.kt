package com.example.metronome.ui.lyrics

import androidx.lifecycle.ViewModel
import com.example.metronome.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val TITLE_MAX_LENGTH = 10

class LyricsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LyricsUiState())
    val uiState: StateFlow<LyricsUiState> = _uiState.asStateFlow()

    private var lyrics = _uiState.value.currentSong.lyrics
    private var notes = _uiState.value.currentSong.notes
    private var lyricsLinesAmount = 0
    private var notesLinesAmount = 0

    var titleForDisplay = ""
        private set

    var iteratorValue = 0
        get() {
            return if (notesLinesAmount > lyricsLinesAmount) {
                notesLinesAmount
            } else {
                lyricsLinesAmount
            }
        }
        private set

    fun setSong(song: Song) {
        _uiState.update { currentState ->
            currentState.copy(currentSong = song)
        }

        lyrics = _uiState.value.currentSong.lyrics.plus('\n')
        notes = _uiState.value.currentSong.notes

        val title = _uiState.value.currentSong.title
        if (title.length >= TITLE_MAX_LENGTH) {
            titleForDisplay = title.plus("         ").plus(title)
            _uiState.update { currentState ->
                currentState.copy(playTitleAnimation = true)
            }
        } else {
            titleForDisplay = title
        }
    }

    fun getNotesLine(): Array<String> {
        var count = 0
        var notesLine = ""
        val notesStrings: MutableList<String> = mutableListOf()

        for (char in lyrics) {
            if (char == '\n') {
                notesStrings.add(notesLine)
                notesLine = ""
            }

            notesLine = if (notes.containsKey(count))
                notesLine.plus(notes[count])
            else
                notesLine.plus(" ")

            ++count
        }

        notesLinesAmount = notesStrings.size
        return notesStrings.toTypedArray()
    }

    fun getLyricsLines(): Array<String> {
        var previousNewLinePos = 0
        var letterCount = 0
        val lyricsLines: MutableList<String> = mutableListOf()

        for (char in lyrics) {
            if (char == '\n') {
                lyricsLines.add(lyrics.substring(previousNewLinePos, letterCount))
                previousNewLinePos = letterCount + 1
            }

            ++letterCount
        }

        lyricsLinesAmount = lyricsLines.size
        return lyricsLines.toTypedArray()
    }
}