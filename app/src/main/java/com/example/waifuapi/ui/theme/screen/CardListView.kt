package com.example.waifuapi.ui.theme.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class) // Opt-in para LazyVerticalGrid
@Composable
fun CardList(photos: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Definir dos columnas fijas
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        contentPadding = PaddingValues(8.dp), // Padding para el contenido
        verticalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre filas
        horizontalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre columnas

    ) {
        items(photos) { imageUrl ->
            CharacterImageCard(imageUrl = imageUrl)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCardList() {
    // Lista de URLs de ejemplo
    val photos = listOf(
        "https://upload.wikimedia.org/wikipedia/en/d/d4/Mickey_Mouse.png",
        "https://upload.wikimedia.org/wikipedia/en/7/77/Donald_Duck.png",
        "https://upload.wikimedia.org/wikipedia/en/d/d4/Mickey_Mouse.png",
        "https://upload.wikimedia.org/wikipedia/en/7/77/Donald_Duck.png",
        "https://upload.wikimedia.org/wikipedia/en/d/d4/Mickey_Mouse.png",
        "https://upload.wikimedia.org/wikipedia/en/7/77/Donald_Duck.png",
        "https://upload.wikimedia.org/wikipedia/en/d/d4/Mickey_Mouse.png",
        "https://upload.wikimedia.org/wikipedia/en/7/77/Donald_Duck.png",
        )
    CardList(photos)
}