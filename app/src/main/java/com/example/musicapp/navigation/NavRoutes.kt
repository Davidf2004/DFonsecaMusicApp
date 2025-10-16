package com.example.musicapp.navigation

object NavRoutes {
    const val HOME = "home"
    const val DETAIL = "detail/{albumId}"
    fun detail(albumId: String) = "detail/$albumId"
}