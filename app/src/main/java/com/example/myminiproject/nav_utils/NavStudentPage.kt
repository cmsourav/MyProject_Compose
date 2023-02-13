package com.example.myminiproject.nav_utils

sealed class NavStudentPage(val route: String) {
    object StudentMainScreen : NavStudentPage(route = "student_main_screen")
    object RegistrationPersonalInfo : NavStudentPage(route = "registration_personal_info")
    object RegistrationCourseDetails : NavStudentPage(route = "registration_course_details")
    object CollegeDetails : NavStudentPage(route = "college_details")
    object FeesDetails : NavStudentPage(route = "fees_details")
    object ReferenceDetails : NavStudentPage(route = "reference_details")
}
