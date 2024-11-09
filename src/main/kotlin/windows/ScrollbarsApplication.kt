package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
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
            var action by remember { mutableStateOf(false) }
            var tooltipState by remember { mutableStateOf(false) }
            Box(
                modifier = Modifier.fillMaxSize().verticalScroll(stateVertical).padding(end = 16.dp, bottom = 16.dp)
                    .horizontalScroll(stateHorizontal),
            ) {
                Row {
                    Column {
                        for (item in 0..30) {
                            TextBox("Item #$item")
                            if (item < 30) {
                                Spacer(modifier = Modifier.height(5.dp))
                            }
                        }

                    }
                    Column {
                        Button(onClick = {
                            action = true
                        }) {
                            Text("第二滚动")
                        }
                        Button(onClick = {
                            tooltipState = true
                        }) {
                            Text("Tooltips 工具提示")
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
            if (action) {
                Window(
                    title = "第二滚动条",
                    state = WindowState(width = 200.dp, height = 400.dp),
                    onCloseRequest = { action = false },
                    resizable = false
                ) {
                    LazyScrollable()
                }
            }
            if (tooltipState) {
                Window(
                    title = "Tooltips 工具提示",
                    state = WindowState(width = 300.dp, height = 400.dp),
                    onCloseRequest = { tooltipState = false },
                    resizable = false
                ) {
                    TooltipBox()
                }
            }

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun TooltipBox() {
    val buttons = listOf("Button A", "Button B", "Button C", "Button D", "Button E", "Button F")
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.spacedBy(5.dp)
    ) {
        buttons.forEachIndexed { index, name ->
            TooltipArea(
                tooltip = {
                    Surface(
                        modifier = Modifier.shadow(4.dp),
                        color = Color(255, 255, 210),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "提示 ${name}",
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                },
                modifier = Modifier.padding(10.dp),
                delayMillis = 600,
                tooltipPlacement = TooltipPlacement.CursorPoint(
                    alignment = Alignment.BottomStart,
                    offset = if (index % 2 == 0) DpOffset(-16.dp, 0.dp) else DpOffset.Zero
                )
            ) {
                Button(onClick = {}) {
                    Text(text = name)
                }
            }
        }
    }
}

@Composable
fun LazyScrollable() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val state = rememberLazyListState()
        LazyColumn(
            state = state,
            modifier = Modifier.fillMaxSize().padding(end = 16.dp),
        ) {
            items(100) { x ->
                TextBox("Item $x")
                Spacer(Modifier.height(5.dp))
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )
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