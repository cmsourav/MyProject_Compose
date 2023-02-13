package com.example.myminiproject.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myminiproject.components.util.TopBarHeader
import kotlinx.coroutines.launch

@Composable
fun FinanceScreen(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        TopBarHeader(
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            },
            screenName = "Finance"
        )

        Text(text = "Finance screen", color = Color.Black)
    }
}
