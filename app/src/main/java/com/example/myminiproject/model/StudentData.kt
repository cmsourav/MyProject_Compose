package com.example.myminiproject.model

import java.util.Date

data class S_personalInfo(
    val first_name: String,
    val last_name: String,
    val fatherName: String,
    val dob: Date,
    val studentMobileNumber: Int,
    val fatherMobileNumber: Int,
    val studentEmail: String
)
