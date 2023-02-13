package com.example.myminiproject.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ButtonUnselectBackground = Color(0xFFDDDDEA)
val BlackTabIcon = Color(0xE9121212)

val CakapSurface: Color
    @Composable get() = if (!isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF3C3C3C)

val CakapBlack: Color
    @Composable get() = if (!isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFFFFFFF)