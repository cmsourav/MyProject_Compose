package com.example.myminiproject.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myminiproject.model.AdminDetails
import com.example.myminiproject.nav_utils.NavDrawerScreens
import com.example.myminiproject.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerPage(
    adminDetails: AdminDetails,
    homeViewModel: HomeViewModel
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(modifier = Modifier.fillMaxSize()) {
                DrawerContentHeader(adminDetails)
                Divider()
                Spacer(modifier = Modifier.height(20.dp))
                DrawerContentBody(scope = scope, navController = navController, drawerState = drawerState)
                Spacer(modifier = Modifier.height(45.dp))
            }
        },
        content = {
            Navigation(navController = navController, drawerState = drawerState, homeViewModel= homeViewModel)
        }
    )
}

@Composable
fun Navigation(navController: NavHostController, drawerState: DrawerState, homeViewModel: HomeViewModel) {
    NavHost(navController, startDestination = NavDrawerScreens.Student.route) {
        composable(NavDrawerScreens.Home.route) {
            HomeScreen(drawerState)
        }
        composable(NavDrawerScreens.Student.route) {
            StudentScreen(drawerState, homeViewModel)
        }
        composable(NavDrawerScreens.Reference.route) {
            ReferenceScreen(drawerState)
        }
        composable(NavDrawerScreens.Finance.route) {
            FinanceScreen(drawerState)
        }
        composable(NavDrawerScreens.Settings.route) {
            SettingsScreen(drawerState)
        }
    }
}

@Composable
fun DrawerContentHeader(adminDetails: AdminDetails) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 15.dp, bottom = 30.dp)
    ) {
        Image(
            painter = painterResource(id = adminDetails.adminImage),
            contentDescription = "user",
            modifier = Modifier.size(55.dp)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = adminDetails.adminName,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            text = adminDetails.adminDesignation,
            fontSize = 14.sp,
            color = Color.LightGray
        )
        Text(
            text = adminDetails.adminEmail,
            fontSize = 14.sp,
            color = Color.LightGray
        )
    }
}

@Composable
fun DrawerContentBody(scope: CoroutineScope, navController: NavController, drawerState: DrawerState) {
    val items = listOf(
        NavDrawerScreens.Home,
        NavDrawerScreens.Student,
        NavDrawerScreens.Reference,
        NavDrawerScreens.Finance,
        NavDrawerScreens.Settings,
        NavDrawerScreens.Logout
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    items.forEach { item ->
        DrawerItems(items = item, selected = currentRoute == item.route, onItemClick = {
            navController.navigate(item.route) {
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
            scope.launch {
                drawerState.close()
            }
        })
    }
}

@Composable
fun DrawerItems(
    items: NavDrawerScreens,
    selected: Boolean,
    onItemClick: (NavDrawerScreens) -> Unit,
    modifier: Modifier = Modifier
) {
    val background = if (selected) Color.LightGray else Color.Transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(items) })
            .height(45.dp)
            .background(color = background)
            .padding(start = 15.dp)
    ) {
        Image(
            painter = painterResource(id = items.icon),
            contentDescription = items.title,
            contentScale = ContentScale.Fit,
            modifier = modifier.size(28.dp),
            colorFilter = ColorFilter.tint(Color.Gray)
        )
        Spacer(modifier = modifier.width(18.dp))
        Text(
            text = items.title,
            fontSize = 17.sp
        )
        Spacer(modifier = modifier.height(20.dp))
    }
}
