package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {

    }
}


fun main() = application {
    Window(
        title = "计时器",
        resizable = false,
        onCloseRequest = ::exitApplication,
        state = WindowState(width = 300.dp, height = 300.dp)
    ) {

    }
}