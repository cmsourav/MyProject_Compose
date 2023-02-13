package com.example.myminiproject.nav_utils

sealed class MainScreens(val route: String) {
    object LoginScreen : MainScreens("login_screen")
    object HomeScreen : MainScreens("home_screen")
}
