package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import utils.StopWatch
import utils.StopWathchDisply

@Composable
@Preview
fun App() {
    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            val stopWatch = remember { StopWatch() }
            StopWathchDisply(
                formatTime = stopWatch.formattedTime,
                onPauseClick = stopWatch::pause,
                onStartClick = stopWatch::start,
                onResetClick = stopWatch::reset
            )
        }
    }
}


fun main() = application {
    Window(
        title = "计时器",
        resizable = false,
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 300.dp, height = 300.dp)
    ) {
        App()
    }
}