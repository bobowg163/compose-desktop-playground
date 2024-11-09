import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MenuBarWeather(isOpen, show)
                Button(onClick = {
                    show.value = true
                    secondWindow = true
                }) {
                    Text("显示第二窗口")
                    if (secondWindow) {
                        var text by remember { mutableStateOf("Hello World!") }
                        var conut by remember { mutableStateOf(0) }
                        Window(
                            title = "第二窗口",
                            onCloseRequest = {
                                secondWindow = false
                            },
                            state = WindowState(width = 300.dp, height = 600.dp),
                            resizable = false
                        ) {
                            MenuBarWeather(isOpen, show)
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    onClick = {
                                        text = "click Count ${conut++}"
                                    },
                                ){
                                    Text("Click me")
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = text,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}
