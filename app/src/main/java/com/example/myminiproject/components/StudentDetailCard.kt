package com.example.myminiproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myminiproject.R
import com.example.myminiproject.model.StudentData
import com.example.myminiproject.theme.PgBackground
import com.example.myminiproject.theme.UgBackground

@Composable
fun StudentDetailCard(studentData: StudentData) {
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = 3.dp,
        backgroundColor = if (studentData.degree == "UG") {
            UgBackground
        } else {
            PgBackground
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = if (studentData.gender == "Male") {
                            R.drawable.ic_man
                        } else {
                            R.drawable.ic_women
                        }
                    ),
                    contentDescription = "user_icon",
                    modifier = Modifier.size(75.dp)
                )
                Column(modifier = Modifier.padding(start = 15.dp, end = 90.dp)) {
                    Row() {
                        Text(
                            text = studentData.first_name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        studentData.last_name?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    Text(
                        text = studentData.category,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    studentData.course?.let {
                        Text(
                            text = it,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Text(
                        text = studentData.college,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = studentData.dateOfAdmission,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_rightarrow),
                    contentDescription = "right_arrow",
                    alignment = Alignment.Center
                )
            }
        }
    }
}