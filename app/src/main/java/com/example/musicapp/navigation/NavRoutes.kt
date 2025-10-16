package com.example.musicapp.navigation

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object Detail : NavRoutes("detail/{albumId}") {
        fun createRoute(albumId: String) = "detail/$albumId"
    }
}