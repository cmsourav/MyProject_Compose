package com.example.myminiproject.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DrawerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myminiproject.components.util.TopBarHeader
import com.example.myminiproject.model.OverView
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    val datas = listOf<OverView>(
        OverView(state = "Tamilnadu", count = 60),
        OverView(state = "Bangalore", count = 60),
        OverView(state = "Mysore", count = 8),
        OverView(state = "Mangalore", count = 5)
    )
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
            screenName = "Home"
        )
        OverViewCardUI(datas)
    }
}

@Composable
fun OverViewCardUI(overView: List<OverView>) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp, start = 5.dp, end = 5.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    text = "KM Foundation",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                overView.forEach {
                    Row() {
                        Text(
                            text = it.state + " : ",
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Text(
                            text = it.count.toString(),
                            fontSize = 18.sp
                        )
                    }
                }
            }
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.Black,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .width(120.dp)
                        .height(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Total",
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "130",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
