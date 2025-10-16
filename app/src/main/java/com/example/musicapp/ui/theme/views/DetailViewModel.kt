package com.example.musicapp.ui.theme.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

class DetailViewModel : ViewModel() {
    var album by mutableStateOf<Album?>(null)
        private set
    var isLoading by mutableStateOf(true)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun load(id: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                album = RetrofitInstance.api.getAlbumDetail(id)
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}