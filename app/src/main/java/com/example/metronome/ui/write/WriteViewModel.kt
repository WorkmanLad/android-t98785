package com.example.metronome.ui.write

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WriteViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WriteUiState())
    val uiState: StateFlow<WriteUiState> = _uiState.asStateFlow()

    fun updateUiState(state: WriteUiState) {
        _uiState.update { currentState ->
            currentState.copy(
                title = state.title,
                artist = state.artist,
                lyrics = state.lyrics,
                notes = state.notes
            )
        }
    }
}
