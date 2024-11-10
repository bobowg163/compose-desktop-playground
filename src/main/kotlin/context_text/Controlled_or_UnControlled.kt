package context_text

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun Event_handlers() {
    var consumedText by remember { mutableStateOf(0) }
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("键盘事件处理 ${consumedText}")
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.onPreviewKeyEvent {
                when {
                    (it.isCtrlPressed && it.key == Key.Minus && it.type == KeyEventType.KeyUp) -> {
                        consumedText -= text.length
                        text = ""
                        true
                    }
                    (it.isCtrlPressed && it.key == Key.Equals && it.type == KeyEventType.KeyUp) -> {
                        consumedText += text.length
                        text = ""
                        true
                    }
                    else -> false
                }

            }
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    Window(
        title = "键盘事件处理",
        onCloseRequest = ::exitApplication,
        icon = painterResource("images/icon.png"),
    ) {
        Event_handlers()
    }

}