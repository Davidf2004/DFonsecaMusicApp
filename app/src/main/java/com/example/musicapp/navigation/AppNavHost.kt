package com.example.musicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicapp.ui.theme.views.DetailScreen
import com.example.musicapp.ui.theme.views.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            HomeScreen(onAlbumClick = { id ->
                navController.navigate(NavRoutes.Detail.createRoute(id))
            })
        }
        composable(NavRoutes.Detail.route) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
            DetailScreen(albumId = albumId, onBack = { navController.popBackStack() })
        }
    }
}