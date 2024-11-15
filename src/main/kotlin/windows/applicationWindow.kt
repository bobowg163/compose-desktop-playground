package windows

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

fun main() = application {
    var filename by remember { mutableStateOf("窗口") }
    var sendonde by remember { mutableStateOf(false) }
    Window(
        onCloseRequest = ::exitApplication,
        title = "$filename: 编辑",
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                modifier = Modifier.size(width = 250.dp, height = 80.dp).padding(16.dp),
                onClick = { filename = "记事本" }
            ) {
                Text("保存")
            }
            Button(
                modifier = Modifier.size(width = 250.dp, height = 80.dp).padding(16.dp),
                onClick = { sendonde = !sendonde }
            ) {
                Text("第二窗口")
            }
        }

        if (sendonde) {
            var action = true
            LaunchedEffect(Unit) {
                delay(1000)
                action = false
            }

            if (action) {
                Window(
                    onCloseRequest = ::exitApplication,
                    title = "action ${action}"
                ) {
                    Text("Performing some tasks. Please wait!")
                }
            } else {
                Window(
                    onCloseRequest = ::exitApplication,
                ) {
                    Text("Hello, World!")
                }
            }

        }

    }
}