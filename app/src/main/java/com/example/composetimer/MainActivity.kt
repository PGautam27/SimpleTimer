package com.example.composetimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetimer.presentation.TimerViewModel
import com.example.composetimer.presentation.component.Controllers
import com.example.composetimer.presentation.component.TimeScreen
import com.example.composetimer.ui.theme.ComposeTimerTheme
import com.example.composetimer.ui.theme.grey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTimerTheme {
                val viewModel by viewModels<TimerViewModel>()
                val times by viewModel.times.collectAsState()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.radialGradient(listOf(grey, Color.White))),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        TimeScreen(times)
                        Controllers(
                            onPause = { viewModel.pause() },
                            onStart = { viewModel.start() },
                            onStop = { viewModel.stop() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTimerTheme {
        Greeting("Android")
    }
}