package com.example.myminiproject.components.util

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyFieldMsg(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Red
) {
    Text(
        text = "*$text",
        color = color,
        fontSize = 12.sp,
        modifier = modifier
    )
}

@Composable
fun TextFieldHeading(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$text : ",
        modifier = modifier.padding(bottom = 8.dp)
    )
}
