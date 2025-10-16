package com.example.musicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.musicapp.ui.theme.views.DetailScreen
import com.example.musicapp.ui.theme.views.HomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.HOME) {
        composable(NavRoutes.HOME) {
            HomeScreen(
                onAlbumClick = { id -> navController.navigate(NavRoutes.detail(id)) }
            )
        }
        composable(
            route = NavRoutes.DETAIL,
            arguments = listOf(navArgument("albumId") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("albumId").orEmpty()
            DetailScreen(albumId = id, onBack = { navController.popBackStack() })
        }
    }
}