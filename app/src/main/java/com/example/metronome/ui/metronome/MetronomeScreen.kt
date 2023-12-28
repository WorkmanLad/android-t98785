package com.example.metronome.ui.metronome

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.metronome.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun MetronomeScreen(modifier: Modifier = Modifier, viewModel: MetronomeViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    if (uiState.playing) {
        LaunchedEffect(viewModel) {
            viewModel.initializeSoundPool(context)
            coroutineScope {
                launch { viewModel.start() }
            }
            viewModel.releaseSoundPool()
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))

        BeatsPerMinutes(
            bpm = uiState.bpm,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(180.dp))

        Circles(
            active = uiState.activeCircle,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(280.dp))

        PlayButton(
            playing = uiState.playing,
            onPlayButton = { viewModel.onPlayButton() },
            onMinusButton = { viewModel.onMinusButton() },
            onPlusButton = { viewModel.onPlusButton() }
        )
    }
}

@Composable
private fun BeatsPerMinutes(
    bpm: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$bpm",
            style = MaterialTheme.typography.displayLarge,
            fontSize = 36.sp,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Text("beats per min", style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun PlayButton(
    playing: Boolean,
    onPlayButton: () -> Unit,
    onMinusButton: () -> Unit,
    onPlusButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        IconButton(onClick = onMinusButton, Modifier.animateContentSize()) {
            Text("-", style = MaterialTheme.typography.headlineLarge, fontSize = 36.sp)
        }
        Card(
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                IconButton(onClick = onPlayButton) {
                    if (!playing) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play",
                            Modifier.size(160.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Pause,
                            contentDescription = "Pause",
                            Modifier.size(160.dp)
                        )
                    }
                }
            }
        }
        IconButton(onClick = onPlusButton, Modifier.animateContentSize()) {
            Text(text = "+", style = MaterialTheme.typography.headlineLarge, fontSize = 36.sp)
        }
    }
}

@Composable
private fun Circles(active: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(4) {
            if (active == it) {
                Icon(imageVector = Icons.Filled.Circle, contentDescription = null)
            } else {
                Icon(imageVector = Icons.Outlined.Circle, contentDescription = null)
            }
        }
    }
}
