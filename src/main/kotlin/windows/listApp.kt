package windows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() = singleWindowApplication(
    title = "列表监控",
    state = WindowState(width = 300.dp, height = 600.dp),
    resizable = false,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        repeat(10) { index ->
            var activate by remember { mutableStateOf(false) }
            Text(
                modifier = Modifier.fillMaxWidth().background(color = if (activate) Color.Blue else Color.White)
                    .onPointerEvent(
                        PointerEventType.Enter
                    ){
                        activate = true
                    }.onPointerEvent(PointerEventType.Exit){
                        activate = false
                    },
                text = "Item $index",
                fontSize = 30.sp,
                fontStyle = if (activate) FontStyle.Italic else FontStyle.Normal
            )
        }
    }
}