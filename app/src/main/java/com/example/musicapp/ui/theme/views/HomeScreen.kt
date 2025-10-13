package com.example.musicapp.ui.theme.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val albums = viewModel.albumList
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Good Morning!",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            error != null -> Text(text = "Error: $error", color = MaterialTheme.colorScheme.error)

            else -> {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(albums) { album ->
                        Card(
                            modifier = Modifier
                                .width(200.dp)
                                .height(220.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column {
                                Image(
                                    painter = rememberAsyncImagePainter(album.image),
                                    contentDescription = album.title,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = album.title,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    text = album.artist,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Recently Played",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(albums) { album ->
                        ListItem(
                            headlineContent = { Text(album.title) },
                            supportingContent = { Text(album.artist) },
                            leadingContent = {
                                Image(
                                    painter = rememberAsyncImagePainter(album.image),
                                    contentDescription = null,
                                    modifier = Modifier.size(56.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}