package context_text

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication(
    title = "Compose for Desktop 中的上下文菜单",
    state = WindowState(width = 400.dp, height = 600.dp),
) {
    val text = remember { mutableStateOf("你好") }
    var action by remember { mutableStateOf(false) }
    Row {
        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            label = { Text(text = "请输入!") }
        )
        Spacer(Modifier.width(10.dp))
        Button(onClick = { action = true }) {
            Text("第二窗口")
        }
    }
    if (action){
        Window(
            title = "第二窗口",
            onCloseRequest = {action = false},
            resizable = false,
        ){
            SelectionContainer {
                Text("文本的上下文菜单仅包含复制操作")
            }

            ContextMenuArea(items = {
                listOf(
                    ContextMenuItem("User-defined Action") {/*do something here*/},
                    ContextMenuItem("Another user-defined action") {/*do something else*/}
                )
            }) {
                Box(modifier = Modifier.background(Color.Blue).height(100.dp).width(100.dp))
            }
        }
    }

}