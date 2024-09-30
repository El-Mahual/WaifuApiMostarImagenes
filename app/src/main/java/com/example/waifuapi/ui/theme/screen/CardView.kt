package com.example.waifuapi.ui.theme.screen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
@Composable
fun CharacterImageCard(imageUrl: String) {
    Card(
        shape = CircleShape,  // Forma de la tarjeta: círculo
        border = BorderStroke(2.dp, Color.Black),  // Borde negro alrededor del círculo
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f) // Proporción 1:1 para que la tarjeta sea siempre cuadrada
            .fillMaxWidth(0.45f), // Control del ancho para asegurar que no sea demasiado grande
        elevation = CardDefaults.cardElevation(8.dp) // Sombra
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Character Image",
            contentScale = ContentScale.Crop, // Ajusta la imagen para que llene el círculo
            modifier = Modifier
                .fillMaxSize() // La imagen llena el tamaño del contenedor
                .clip(CircleShape) // Recorta la imagen en forma circular
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCharacterImageCard() {
    // URL de muestra
    val imageUrl = "https://upload.wikimedia.org/wikipedia/en/d/d4/Mickey_Mouse.png"

    // Llamamos a la tarjeta de imagen con la URL de muestra
    CharacterImageCard(imageUrl = imageUrl)
}
