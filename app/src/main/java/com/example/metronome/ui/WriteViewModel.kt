package com.example.metronome.ui

import androidx.lifecycle.ViewModel
import com.example.metronome.data.WriteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WriteViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WriteUiState())
    val uiState: StateFlow<WriteUiState> = _uiState.asStateFlow()
}