package windows

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FocusableBox(text: String = "", onClick: () -> Unit = {}, size: IntSize = IntSize(200, 35)) {
    val keyPressedState = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor = if (interactionSource.collectIsFocusedAsState().value) {
        if (keyPressedState.value)
            lerp(MaterialTheme.colors.secondary, Color(64, 64, 64), 0.5f)
        else
            MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.primary
    }

    Box(
        modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(backgroundColor)
            .size(size.width.dp, size.height.dp).onPointerEvent(
                PointerEventType.Press
            ) { onClick() }.onPreviewKeyEvent {
                if (
                    it.key == Key.Enter ||
                    it.key == Key.Spacebar
                ) {
                    when (it.type) {
                        KeyEventType.KeyDown -> {
                            keyPressedState.value = true
                        }

                        KeyEventType.KeyUp -> {
                            keyPressedState.value = false
                            onClick.invoke()
                        }
                    }
                }
                false
            }.focusable(interactionSource = interactionSource),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White)
    }
}


fun main() = application {
    Window(
        state = WindowState(size = DpSize(350.dp, 600.dp)),
        onCloseRequest = ::exitApplication,
        title = "选项卡式导航和键盘焦点",
    ) {

        var isVisible by remember { mutableStateOf(false) }

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                for (x in 1..5) {
                    val text = remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text.value,
                        singleLine = true,
                        onValueChange = { text.value = it },
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Button(onClick = { isVisible = true }) {
                    Text("第二窗口")
                }
            }
        }

        if (isVisible) {
            Window(
                onCloseRequest = { isVisible = false },
                title = "要使不可聚焦的组件可聚焦，您需要对组件应用",
                state = WindowState(size = DpSize(350.dp, 600.dp)),
            ) {
                MaterialTheme(
                    colors = MaterialTheme.colors.copy(
                        primary = Color(10, 132, 232),
                        secondary = Color(150, 232, 150)
                    ),
                ) {
                    val clicks = remember { mutableStateOf(0) }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            modifier = Modifier.padding(40.dp)
                        ) {
                            Text(text = "单击：${clicks.value}")
                            Spacer(modifier = Modifier.height(20.dp))
                            for (x in 1..5) {
                                FocusableBox("按扭 $x", { clicks.value++ })
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }
            }
        }

    }
}