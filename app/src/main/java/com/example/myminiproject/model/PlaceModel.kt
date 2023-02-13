package com.example.myminiproject.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ReportModel(
    val label: String = "",
    var state: MutableState<Boolean> = mutableStateOf(false)
)
data class CounterModel(
    val counter: MutableState<Int> = mutableStateOf(0)
)
