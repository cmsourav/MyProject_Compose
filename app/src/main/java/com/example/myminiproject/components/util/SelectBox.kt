package com.example.myminiproject.components.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myminiproject.model.ReportModel
import com.example.myminiproject.theme.BlackTabIcon
import com.example.myminiproject.theme.ButtonUnselectBackground
import com.example.myminiproject.theme.CakapBlack
import com.example.myminiproject.theme.CakapSurface
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun ReportList(
    report: List<ReportModel>,
    place: MutableState<String>
) {
    FlowRow(
        modifier = Modifier
            .background(color = Color.White)
            .padding(start = 8.dp),
        mainAxisAlignment = MainAxisAlignment.Start,
        mainAxisSize = SizeMode.Wrap,
        crossAxisSpacing = 16.dp,
        mainAxisSpacing = 8.dp
    ) {
        report.forEach { reportValue ->
            Text(
                text = reportValue.label,
                fontSize = 14.sp,
                letterSpacing = .4.sp,
                color = if (reportValue.state.value) {
                    Color.White
                } else {
                    Color.Black
                },
                modifier = Modifier
                    .border(
                        border = BorderStroke(1.dp, ButtonUnselectBackground),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        color = if (reportValue.state.value) {
                            BlackTabIcon
                        } else {
                            Color.White
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        reportValue.state.value = !reportValue.state.value
                    }
                    .padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
    report.forEachIndexed { index, reportModel ->
        if (reportModel.state.value) {
            val category = when (index) {
                0 -> "TamilNadu"
                1 -> "Bangalore"
                2 -> "Mangalore"
                3 -> "Mysore"
                4 -> "Kerala"
                5 -> "Other"
                else -> "OTHERS"
            }
            place.value = category
        }
    }
}
