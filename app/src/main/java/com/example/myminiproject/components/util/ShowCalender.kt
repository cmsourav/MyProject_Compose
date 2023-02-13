package com.example.myminiproject.components.util

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import java.util.*

class ShowCalender {

    @Composable
    fun MyContent(
        mContext: Context,
        mDate: MutableState<String>
    ) {
        val mYear: Int
        val mMonth: Int
        val mDay: Int

        val mCalendar = Calendar.getInstance()

        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

        mCalendar.time = Date()

        val mDatePickerDialog = DatePickerDialog(
            mContext,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            },
            mYear,
            mMonth,
            mDay
        )

//    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//        Button(onClick = {
//            mDatePickerDialog.show()
//        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))) {
//            Text(text = "Open Date Picker", color = Color.White)
//        }
//
//        Spacer(modifier = Modifier.size(100.dp))
//        Text(text = "Selected Date: ${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
//    }
    }
}