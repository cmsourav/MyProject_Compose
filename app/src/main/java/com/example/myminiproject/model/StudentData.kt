package com.example.myminiproject.model

data class StudentData(
    val first_name: String,
    val last_name: String?= null,
    val category: String,
    val course: String? = null,
    val college: String,
    val studentMobileNumber: String,
    val gender: String,
    val dateOfAdmission: String,
    val degree: String
)
//val gender: String,
//val dob: String,
//val fatherName: String,
//val studentMobileNumber: String,
//val fatherMobileNumber: String,
//val studentEmail: String,
//val degree: String,
//val category: String,
//val course: String,
//val academicYear: String,
//val dateOfAdmission: String,
//val certificateCollected: String,
//val place: String,
//val college: String