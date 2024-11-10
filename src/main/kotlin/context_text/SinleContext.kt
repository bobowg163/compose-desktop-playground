package context_text

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() = singleWindowApplication(
    title = "Compose for Desktop 中的上下文菜单",
    state = WindowState(width = 400.dp, height = 600.dp),
){
    val text = remember { mutableStateOf("你好") }
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(text = "请输入!")}
    )
}