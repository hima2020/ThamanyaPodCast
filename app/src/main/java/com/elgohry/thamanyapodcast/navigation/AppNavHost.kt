package com.elgohry.thamanyapodcast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elgohry.feature_home.presentation.screens.HomeScreen
import com.elgohry.feature_search.presentation.screens.SearchScreen

@Composable
fun AppNavHost() {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onSearchClick = { nav.navigate(Routes.SEARCH) { launchSingleTop = true } }
            )
        }
        composable(Routes.SEARCH) {
            SearchScreen(
                onBack = { nav.popBackStack() }
            )
        }
    }
}