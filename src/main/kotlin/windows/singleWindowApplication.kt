package windows

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

/**
 * @作者 bobo
 * @项目 Compose-for-desktopplayground
 * @日期 2024/11/1 时间 下午6:42
 * November01日Friday
 */

@Preview
@Composable
fun singleWindowApplication() {
    Image(
        painter = painterResource("images/painter.jpg"),
        contentDescription = "Painter",
        modifier = Modifier.fillMaxSize()
    )
}

