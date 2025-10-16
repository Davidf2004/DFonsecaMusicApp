package com.example.musicapp.ui.theme.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.musicapp.data.model.Album
import com.example.musicapp.ui.theme.components.MiniPlayer

@Composable
fun DetailScreen(albumId: String, onBack: () -> Unit, vm: DetailViewModel = viewModel()) {
    LaunchedEffect(albumId) { vm.load(albumId) }

    val alb: Album? = vm.album

    Box(Modifier.fillMaxSize()) {
        when {
            vm.isLoading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            vm.errorMessage != null -> Text("Error: ${vm.errorMessage}", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            alb != null -> {
                LazyColumn(contentPadding = PaddingValues(bottom = 96.dp)) {
                    item { HeaderImage(alb, onBack) }
                    item { AboutCard(alb) }
                    item { ArtistChip(alb.artist) }
                    items((1..10).toList()) { i ->
                        TrackItem(
                            image = alb.image,
                            title = "${alb.title} • Track $i",
                            artist = alb.artist
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }
                MiniPlayer(album = alb, modifier = Modifier.align(Alignment.BottomCenter))
            }
        }
    }
}

@Composable
private fun HeaderImage(album: Album, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(28.dp))
    ) {
        Image(
            painter = rememberAsyncImagePainter(album.image),
            contentDescription = album.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            Modifier
                .matchParentSize()
                .background(Brush.verticalGradient(listOf(Color(0x00000000), Color(0x992A1046)))))
        Box(Modifier.fillMaxSize().padding(20.dp)) {
            IconButton(onClick = onBack, modifier = Modifier.align(Alignment.TopStart)) {
                Text("←", color = Color.White, style = MaterialTheme.typography.titleLarge)
            }
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(album.title, color = Color.White, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)
                Text(album.artist, color = Color(0xFFE6DFFF), style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    CircleButton("▶")
                    CircleButton("🔀")
                }
            }
        }
    }
}

@Composable private fun CircleButton(symbol: String) {
    Box(
        modifier = Modifier.size(56.dp).clip(CircleShape).background(Color(0xFF7A4CF4)),
        contentAlignment = Alignment.Center
    ) { Text(symbol, color = Color.White, style = MaterialTheme.typography.titleMedium) }
}

@Composable private fun AboutCard(album: Album) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("About this album", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text(album.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
    Spacer(Modifier.height(12.dp))
}

@Composable private fun ArtistChip(artist: String) {
    Row(Modifier.padding(horizontal = 16.dp)) {
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(24.dp),
            shadowElevation = 4.dp
        ) {
            Text(
                text = "Artist: $artist",
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    Spacer(Modifier.height(12.dp))
}

@Composable private fun TrackItem(image: String, title: String, artist: String) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        shape = RoundedCornerShape(22.dp), elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
                modifier = Modifier.size(52.dp).clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(artist, style = MaterialTheme.typography.bodySmall, color = Color(0xFF6C6C6C))
            }
            Text("⋮", color = Color(0xFF9A9A9A))
        }
    }
}