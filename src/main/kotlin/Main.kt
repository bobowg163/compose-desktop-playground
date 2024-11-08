import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    val isOpen = remember { mutableStateOf(true) }
    val show = rememberSaveable { mutableStateOf(true) }
    var secondWindow by remember { mutableStateOf(false) }
    if (isOpen.value) {
        isOpen.value = BuildTray(isOpen, show)
        Window(
            onCloseRequest = { isOpen.value = false },
            title = "Compose Desktop playground",
            icon = painterResource("images/icon.png"),
        ) {

            Column {
                MenuBarWeather(isOpen, show)
                Button(onClick = {
                    show.value = true
                    secondWindow = true
                }) {
                    Text("显示第二窗口")
                    if (secondWindow) {
                        Window(
                            title = "new Windows",
                            onCloseRequest = {
                                secondWindow = false
                            }
                        ) {
                            MenuBarWeather(isOpen, show)
                        }
                    }
                }
            }


        }
    }
}
