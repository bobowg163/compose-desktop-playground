package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants.EXIT_ON_CLOSE

val northClicks = mutableStateOf(0)
val westClicks = mutableStateOf(0)
val eastClicks = mutableStateOf(0)

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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row {
            Counter("西", westClicks)
            Spacer(modifier = Modifier.width(25.dp))
            Counter("北", northClicks)
            Spacer(modifier = Modifier.width(25.dp))
            Counter("东", eastClicks)
        }
    }
}

@Composable
fun Counter(text: String, counter: MutableState<Int>) {
    Surface(
        modifier = Modifier.size(130.dp, 130.dp),
        color = Color(180, 180, 180),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column {
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