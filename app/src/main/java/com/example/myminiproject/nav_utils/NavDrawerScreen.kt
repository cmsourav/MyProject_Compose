package com.example.myminiproject.nav_utils

import com.example.myminiproject.R

sealed class NavDrawerScreens(val route: String, val icon: Int, val title: String) {
    object Home : NavDrawerScreens("home", R.drawable.ic_baseline_home_24, "Dashboard")
    object Student : NavDrawerScreens("student", R.drawable.ic_baseline_person_24, "Students")
    object Reference : NavDrawerScreens("college", R.drawable.ic_baseline_home_work_24, "Reference")
    object Finance : NavDrawerScreens("finance", R.drawable.ic_baseline_account_balance_24, "Finance")
    object Settings : NavDrawerScreens("settings", R.drawable.ic_baseline_settings_24, "Settings")
    object Logout : NavDrawerScreens("settings", R.drawable.baseline_login_24, "Log out")
}