package com.example.metronome.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metronome.ui.theme.MetronomeTheme

@Composable
fun WriteScreen(modifier: Modifier = Modifier, viewModel: WriteViewModel = viewModel()) {
    Column(modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = "Title",
                textStyle = MaterialTheme.typography.displayLarge,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            TextField(
                value = "Artist",
                textStyle = MaterialTheme.typography.displaySmall,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                onValueChange = {},
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        OutlinedTextField(
            value = "Notes",
            textStyle = MaterialTheme.typography.labelLarge,
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = Color(0.2f, 0.8f, 0.68f),
                unfocusedContainerColor = Color.Transparent
            ),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = "Lyrics",
            textStyle = MaterialTheme.typography.bodyLarge,
            colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
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