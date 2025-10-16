package com.example.musicapp.data.network

import com.example.musicapp.data.model.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicApiService {

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: String): Album
}