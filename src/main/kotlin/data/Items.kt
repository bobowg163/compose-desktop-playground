package data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Item(
    val text:String,
    val icon:ImageVector,
    val color: Color,
    val state:MutableState<Boolean> = mutableStateOf(false)
)

