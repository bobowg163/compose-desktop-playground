import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun App() {
    MaterialTheme {
        val text = remember { mutableStateOf("Hello, World!") }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = { text.value = "Hello ,Desktop!" }) {
                Text(text.value)
            }
        }
    }
}


fun main() = application {
    val isOpen = remember { mutableStateOf(true) }
    val show = rememberSaveable { mutableStateOf(true) }
    if (isOpen.value) {
        isOpen.value = BuildTray(isOpen, show)
        Window(
            onCloseRequest = { isOpen.value = false },
            title = "Compose Desktop playground",
            icon = painterResource("images/icon.png"),
        ) {
            App()
            MenuBarWeather(isOpen, show)
        }
    }
}
