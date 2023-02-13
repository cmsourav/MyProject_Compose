package com.example.myminiproject.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myminiproject.nav_utils.MainScreens

class HomeViewModel : ViewModel() {
    var isLoginCredEmpty: MutableState<Boolean> = mutableStateOf(false)
    var isLoginMismatch: MutableState<Boolean> = mutableStateOf(false)

    fun validateLoginCredential(
        userEmail: MutableState<String>,
        password: MutableState<String>,
        navController: NavController
    ) {
        val nam = "admin@pzd"
        val pas = "12345678"

        if (userEmail.value.isBlank() || password.value.isBlank()) {
            isLoginCredEmpty.value = true
        } else {
            isLoginCredEmpty.value = false
            if (nam == userEmail.value && pas == password.value) {
                isLoginMismatch.value = false
                navController.navigate(MainScreens.HomeScreen.route)
            } else {
                isLoginMismatch.value = nam != userEmail.value || pas != password.value
            }
        }
    }
    fun testing(){
        Log.e("sss", "/////////")
    }
}
