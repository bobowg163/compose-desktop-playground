package utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopWathchDisply(
    formatTime: String,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTime,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color.Black,
        )
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onStartClick) {
                Text("开始",)
            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = onPauseClick) {
                Text(
                    "暂停",
                )

            }
            Spacer(Modifier.width(16.dp))
            Button(onClick = onResetClick) {
                Text(
                    "重置",

                    )

            }
        }
    }
}