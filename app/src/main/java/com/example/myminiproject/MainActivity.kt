package com.example.myminiproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myminiproject.components.DrawerPage
import com.example.myminiproject.components.LoginPage
import com.example.myminiproject.model.AdminDetails
import com.example.myminiproject.nav_utils.MainScreens
import com.example.myminiproject.ui.theme.MyMiniProjectTheme
import com.example.myminiproject.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
            MyMiniProjectTheme {
                MainScreenNavigation()
            }
        }
    }

    @Composable
    fun MainScreenNavigation() {
        val adminData = AdminDetails(
            adminImage = R.drawable.user_img,
            adminName = "Sourav C M",
            adminDesignation = "Asst. Manager",
            adminEmail = "sourav@gmail.com"
        )
        var loginEmail = remember { mutableStateOf("") }
        var loginPassword = remember { mutableStateOf("") }

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = MainScreens.HomeScreen.route
        ) {
            composable(route = MainScreens.LoginScreen.route) {
                LoginPage(
                    userEmail = loginEmail,
                    password = loginPassword,
                    onClick = {
                        homeViewModel.validateLoginCredential(
                            userEmail = loginEmail,
                            password = loginPassword,
                            navController
                        )
                    },
                    isEmptyCred = homeViewModel.isLoginCredEmpty,
                    isLoginCredMismatch = homeViewModel.isLoginMismatch
                )
            }
            composable(route = MainScreens.HomeScreen.route) {
                DrawerPage(adminDetails = adminData, homeViewModel = homeViewModel)
            }
        }
    }
}
