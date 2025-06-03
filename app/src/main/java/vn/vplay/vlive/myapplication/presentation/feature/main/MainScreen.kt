package vn.vplay.vlive.myapplication.presentation.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.vplay.vlive.myapplication.presentation.components.BottomNavItem
import vn.vplay.vlive.myapplication.presentation.components.BottomNavigationBar
import vn.vplay.vlive.myapplication.presentation.feature.home.HomeScreen
import vn.vplay.vlive.myapplication.presentation.theme.Color131313

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color131313)
            .padding(
                bottom = WindowInsets.navigationBars
                    .asPaddingValues()
                    .calculateBottomPadding()
            )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavigationBar(navController = navController) },
            content = { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = BottomNavItem.Home.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(BottomNavItem.Home.route) { HomeScreen() }
                    composable(BottomNavItem.Category.route) { HomeScreen() }
                    composable(BottomNavItem.Schedule.route) { HomeScreen() }
                    composable(BottomNavItem.Notification.route) { HomeScreen() }
                    composable(BottomNavItem.Profile.route) { HomeScreen() }
                }
            }
        )
    }
}
