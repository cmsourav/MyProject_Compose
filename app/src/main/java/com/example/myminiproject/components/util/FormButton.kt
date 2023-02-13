package com.example.myminiproject.components.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myminiproject.R

@Composable
fun SingleButton(
    onSingleButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    btnBackground: Color = Color.LightGray
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { onSingleButtonClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 25.dp)
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = btnBackground,
                contentColor = Color.White
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_right_alt_24),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun DoubleButton(
    onDoubleButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
    }
}
