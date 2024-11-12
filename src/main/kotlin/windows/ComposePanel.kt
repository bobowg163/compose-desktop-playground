package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import javax.swing.*
import javax.swing.WindowConstants.EXIT_ON_CLOSE

val northClicks = mutableStateOf(0)
val westClicks = mutableStateOf(0)
val eastClicks = mutableStateOf(0)

val swingLabel = JLabel()

fun main() = SwingUtilities.invokeLater {
    val window = JFrame()
    //创建CoposePanel
    val composePanel = ComposePanel()
    window.defaultCloseOperation = EXIT_ON_CLOSE
    window.title = "Compose Multiplatform 与 Swing 的集成"
    window.contentPane.add(actionButton("北区", action = { northClicks.value++ }), BorderLayout.NORTH)
    window.contentPane.add(actionButton("西区", action = { westClicks.value++ }), BorderLayout.WEST)
    window.contentPane.add(actionButton("东区", action = { eastClicks.value++ }), BorderLayout.EAST)
    window.contentPane.add(
        actionButton(text = "南区移除", action = { window.contentPane.remove(composePanel) }),
        BorderLayout.SOUTH
    )
    window.contentPane.add(composePanel, BorderLayout.CENTER)

    //设置中心
    composePanel.setContent {
        ComposeContent()
    }
    window.setSize(800, 600)
    window.isVisible = true
}

@Composable
@Preview
fun ComposeContent() {
    var action by remember { mutableStateOf(false) }
    var action1 by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Row {
                Counter("西", westClicks)
                Spacer(modifier = Modifier.width(25.dp))
                Counter("北", northClicks)
                Spacer(modifier = Modifier.width(25.dp))
                Counter("东", eastClicks)
                Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                    Column {
                        Button(onClick = { action = true }) {
                            Text(text = "第二窗口")
                        }
                        Button(onClick = { action1 = true }) {
                            Text(text = "第三窗口")
                        }
                    }

                }

            }
        }
        if (action) {
            Window(
                title = "使用 SwingPanel 将 Swing 组件添加到 Compose Multiplatform 组合中",
                onCloseRequest = { action = false },
                state = WindowState(width = 400.dp, height = 400.dp)
            ) {
                val counter = remember { mutableStateOf(0) }
                val inc: () -> Unit = { counter.value++ }
                val dec: () -> Unit = { counter.value-- }
                Box(
                    modifier = Modifier.fillMaxWidth().height(60.dp).padding(top = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Counter: ${counter.value}")
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(top = 80.dp, bottom = 20.dp)
                    ) {
                        Button("1. Compose Button: increment", inc)
                        Spacer(modifier = Modifier.height(20.dp))

                        SwingPanel(
                            background = Color.White,
                            modifier = Modifier.size(270.dp, 90.dp),
                            factory = {
                                JPanel().apply {
                                    layout = BoxLayout(this, BoxLayout.Y_AXIS)
                                    add(actionButton1("1. Swing Button: decrement", dec))
                                    add(actionButton1("2. Swing Button: decrement", dec))
                                    add(actionButton1("3. Swing Button: decrement", dec))
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        Button("2. Compose Button: increment", inc)
                    }
                }

            }
        }

        if (action1) {
            Window(
                onCloseRequest = { action1 = false },
                title = "当 Compose 状态更改时更新 Swing 组件",
                state = WindowState(width = 400.dp, height = 200.dp)
            ) {
                val click = remember { mutableStateOf(0) }
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SwingPanel(
                        modifier = Modifier.fillMaxWidth().height(40.dp),
                        factory = {
                            JPanel().apply {
                                add(swingLabel, BorderLayout.CENTER)
                            }
                        },
                        update = {
                            swingLabel.setText("SwingLabel Clicks: ${click.value}")
                        }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Row(modifier = Modifier.height(40.dp), verticalAlignment = Alignment.CenterVertically) {
                       Button(onClick = {click.value++}){
                            Text("相加")
                        }
                        Spacer(modifier = Modifier.width(40.dp))
                        Button(onClick = {click.value--}){
                            Text("相减")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Button(text: String = "", onClick: (() -> Unit)? = null) {
    Button(modifier = Modifier.size(270.dp, 30.dp), onClick = { onClick?.invoke() }) {
        Text(text)
    }
}

fun actionButton1(
    text: String,
    action: () -> Unit
): JButton {
    val button = JButton(text)
    button.alignmentX = Component.CENTER_ALIGNMENT
    button.addActionListener { action() }
    return button
}

@Composable
fun Counter(text: String, counter: MutableState<Int>) {
    Surface(
        modifier = Modifier.size(130.dp, 130.dp),
        color = Color(180, 180, 180),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(modifier = Modifier.height(30.dp).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(text = "${text}单击：${counter.value}")
            }
            Spacer(modifier = Modifier.height(25.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { counter.value++ }) {
                    Text(text = text, color = Color.White)
                }
            }
        }
    }
}


fun actionButton(text: String, action: (() -> Unit)? = null): JButton {
    val button = JButton(text)
    button.toolTipText = "提示${text}按钮"
    button.preferredSize = Dimension(100, 100)
    button.addActionListener { action?.invoke() }
    return button
}