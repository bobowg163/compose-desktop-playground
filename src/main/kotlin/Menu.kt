import androidx.compose.runtime.*
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar

/**
 * @作者 bobo
 * @项目 Compose-for-desktopplayground
 * @日期 2024/11/2 时间 上午8:02
 * November02日Saturday
 */


@Composable
fun FrameWindowScope.MenuBarWeather(isOpen: MutableState<Boolean>, showMenu: MutableState<Boolean>): Boolean {
    var isSubmenuShowing by remember { mutableStateOf(false) }
    var action by remember { mutableStateOf("Last action None") }
    MenuBar {
        Menu("文件", mnemonic = 'F') {
            Item(
                "复制（功能还没有完成）",
                onClick = { action = "Last action None" },
                shortcut = KeyShortcut(Key.C, ctrl = true)
            )
            Item(
                "粘贴（功能还没有完成）",
                onClick = { action = "Last action None" },
                shortcut = KeyShortcut(Key.V, ctrl = true)
            )
            Separator()
            Item("退出", onClick = {isOpen.value = false}, shortcut = KeyShortcut(Key.Escape), mnemonic = 'E')
        }
        Menu("显示", mnemonic = 'A') {
            CheckboxItem("显示托盘", checked = showMenu.value, onCheckedChange = { showMenu.value = !showMenu.value })
            CheckboxItem(
                "高级设置",
                checked = isSubmenuShowing,
                onCheckedChange = { isSubmenuShowing = !isSubmenuShowing })
            if (isSubmenuShowing) {
                Menu("设置") {
                    Item("设置一", onClick = { action = "Last action: Setting 1" })
                    Item("设置二", onClick = { action = "Last action: Setting 2" })
                }
            }
            Separator()
            Item("关于",icon = painterResource("image/launcher.png"),onClick = { action = "关于我们" })

        }
        Menu("帮助", mnemonic = 'H') {
            Item("天气帮助", onClick = { action = "Last action: Help" })
        }
    }
    println("action:$action")
    return showMenu.value
}
