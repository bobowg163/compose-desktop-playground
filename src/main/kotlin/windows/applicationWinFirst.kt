package windows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.delay

fun main() = application {
    var isVisible by remember { mutableStateOf(false) }
    val state = rememberWindowState(placement = WindowPlacement.Maximized)
    Window(
        onCloseRequest = { isVisible = false },
        title = "Counter",
        visible = isVisible
    ) {
        var counter by remember { mutableStateOf(0) }
        LaunchedEffect(Unit) {
            while (true) {
                counter++
                delay(1000)
            }
        }
        Text(counter.toString())
    }
    if (!isVisible) {
        Tray(
            TrayIcon,
            tooltip = "Counter",
            onAction = { isVisible = true },
            menu = {
                Item("Exit", onClick = ::exitApplication)
            },
        )

        Window(
            onCloseRequest = ::exitApplication,
            state = state,
            title = "更改窗口的状态（最大化、最小化、全屏、大小、位置）"
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        state.placement == WindowPlacement.Fullscreen,
                        {
                            state.placement = if (it) {
                                WindowPlacement.Fullscreen
                            } else {
                                WindowPlacement.Floating
                            }
                        }
                    )
                    Text("全屏")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        state.placement == WindowPlacement.Maximized,
                        {
                            state.placement = if (it) {
                                WindowPlacement.Maximized
                            } else {
                                WindowPlacement.Floating
                            }
                        }
                    )
                    Text("最大化")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(state.isMinimized, { state.isMinimized = !state.isMinimized })
                    Text("isMinimized")
                }

                Text(
                    "Position ${state.position}",
                    Modifier.clickable {
                        val position = state.position
                        if (position is WindowPosition.Absolute) {
                            state.position = position.copy(x = state.position.x + 10.dp)
                        }
                    }
                )

                Text(
                    "Size ${state.size}",
                    Modifier.clickable {
                        state.size = state.size.copy(width = state.size.width + 10.dp)
                    }
                )

            }

        }
    }
}