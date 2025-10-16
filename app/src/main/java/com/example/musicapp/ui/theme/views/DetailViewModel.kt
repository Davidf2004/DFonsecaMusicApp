package com.example.musicapp.ui.theme.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.data.model.Album
import com.example.musicapp.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    var album by mutableStateOf<Album?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun load(id: String) {
        if (id.isBlank()) {
            errorMessage = "Album ID vacío"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val result = RetrofitInstance.api.getAlbum(id)
                album = result
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "Error al cargar el álbum"
            } finally {
                isLoading = false
            }
        }
    }
}