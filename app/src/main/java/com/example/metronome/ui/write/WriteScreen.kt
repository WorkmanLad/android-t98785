package com.example.metronome.ui.write

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metronome.ui.theme.MetronomeTheme

@Composable
fun WriteScreen(modifier: Modifier = Modifier, viewModel: WriteViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    val titleScrollState = rememberScrollState(0)

    Column(modifier) {
        Text(
            text = "Set the song details:",
            style = MaterialTheme.typography.headlineMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = uiState.value.title,
                textStyle = MaterialTheme.typography.displayLarge,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                maxLines = 1,
                onValueChange = { viewModel.updateUiState(uiState.value.copy(title = it)) },
                modifier = Modifier.fillMaxWidth(0.5f).horizontalScroll(state = titleScrollState)
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            TextField(
                value = uiState.value.artist,
                textStyle = MaterialTheme.typography.displaySmall,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                onValueChange = { viewModel.updateUiState(uiState.value.copy(artist = it)) },
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            text = "Start with the lyrics:",
            style = MaterialTheme.typography.headlineMedium
        )

        TextField(
            value = uiState.value.lyrics,
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            onValueChange = { viewModel.updateUiState(uiState.value.copy(lyrics = it)) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun WriteNotes(textFieldValue: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = textFieldValue,
        textStyle = MaterialTheme.typography.labelLarge,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = Color(0.2f, 0.8f, 0.68f),
            unfocusedContainerColor = Color.Transparent
        ),
        maxLines = 1,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun Preview() {
    MetronomeTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            WriteScreen(
                Modifier
                    .fillMaxSize()
                    .padding(30.dp))
        }
    }
}