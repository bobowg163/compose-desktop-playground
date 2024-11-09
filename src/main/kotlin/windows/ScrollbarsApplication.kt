package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@Composable
@Preview
fun ScrollbarApp() {
    MaterialTheme {
        Box(
            Modifier.fillMaxSize().background(color = Color(180, 180, 180)).padding(16.dp),
        ) {
            val stateVertical = rememberScrollState(0)
            val stateHorizontal = rememberScrollState(0)

            Box(
                modifier = Modifier.fillMaxSize().verticalScroll(stateVertical).padding(end = 16.dp, bottom = 16.dp)
                    .horizontalScroll(stateHorizontal),
            ) {
                Column {
                    for (item in 0..30) {
                        TextBox("Item #$item")
                        if (item < 30) {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }

            }
            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(stateVertical)
            )
            HorizontalScrollbar(
                modifier = Modifier.align(Alignment.BottomEnd).fillMaxWidth().padding(12.dp),
                adapter = rememberScrollbarAdapter(stateHorizontal)
            )
        }
    }
}


@Composable
fun TextBox(text: String = "Item") {
    Box(
        modifier = Modifier.height(32.dp).width(400.dp).background(color = Color(0, 0, 0, 20))
            .padding(start = 8.dp, end = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = text)
    }
}


fun main() = application {
    Window(
        title = "滚动和滚动条",
        state = WindowState(width = 250.dp, height = 400.dp),
        onCloseRequest = ::exitApplication,
    ) {
        ScrollbarApp()
    }
}