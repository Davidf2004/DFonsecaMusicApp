package com.example.musicapp.data.model

import com.google.gson.annotations.SerializedName

data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val description: String,
    @SerializedName("image") val imageUrl: String
)