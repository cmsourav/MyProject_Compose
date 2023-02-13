package com.example.myminiproject.components.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myminiproject.R

@Composable
fun TopBarHeader(
    openDrawer: () -> Unit,
    screenName: String,
    icon: Int = R.drawable.ic_baseline_search_24
) {
    TopAppBar(
        elevation = 6.dp,
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                    contentDescription = "list",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(38.dp)
                        .padding(start = 8.dp)
                        .clickable {
                            openDrawer()
                        }
                )
                Text(
                    text = screenName,
                    color = Color.Black,
                    fontWeight = FontWeight.Black
                )
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "list",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable {}
                )
            }
        },
        backgroundColor = Color.White
    )
}