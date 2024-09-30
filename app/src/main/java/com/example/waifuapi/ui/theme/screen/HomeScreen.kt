package com.example.waifuapi.ui.theme.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waifuapi.R
import com.example.waifuapi.viewmodel.WaifuUiState
import com.example.waifuapi.viewmodel.WaifuViewModel


@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(text = photos)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center  // Centra el contenido en la pantalla
    ) {
        // Imagen de carga
        Image(
            painter = painterResource(id = R.drawable.loader),  // Asegúrate de tener esta imagen en tu carpeta "drawable"
            contentDescription = "Loading",
            modifier = Modifier.size(100.dp)  // Tamaño de la imagen
        )
    }
}


@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center  // Centra el contenido en la pantalla
    ) {
        // Imagen de carga
        Image(
            painter = painterResource(id = R.drawable.error_load),  // Asegúrate de tener esta imagen en tu carpeta "drawable"
            contentDescription = "Loading",
            modifier = Modifier.size(100.dp)  // Tamaño de la imagen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Obtén el ViewModel
    val waifuViewModel: WaifuViewModel = viewModel()  // O hiltViewModel() si estás usando Hilt
    val waifuUiState = waifuViewModel.waifuUiState  // Accede al estado del ViewModel

    Column(modifier = modifier.fillMaxSize()) {
        // Barra superior
        TopAppBar(
            title = {
                Text(
                    "WaifuApp",
                    color = Color.Magenta,  // Color del texto
                    modifier = Modifier.fillMaxWidth(),  // Expande el texto a lo largo de la barra
                    textAlign = TextAlign.Center,  // Centra el texto
                    fontFamily = FontFamily.Serif,  // Cambia la fuente (puede ser Sans, Serif, Monospace)
                    fontWeight = FontWeight.Bold,  // Cambia el peso de la fuente a negrita (opcional)
                    fontStyle = FontStyle.Italic  // Cambia el estilo a cursiva (opcional)
                )
                
            }
        )

        // Pantalla según el estado de la UI
        when (waifuUiState) {
            is WaifuUiState.Loading -> {
                // Pantalla de carga cuando el estado es "Loading"
                LoadingScreen(modifier = modifier.fillMaxSize())
            }
            is WaifuUiState.Success -> {
                // Pantalla de resultados cuando el estado es "Success"
                CardList(photos = waifuUiState.photos, modifier = modifier.fillMaxSize())  // Muestra la lista de imágenes
            }
            is WaifuUiState.Error -> {
                // Pantalla de error cuando el estado es "Error"
                ErrorScreen(modifier = modifier.fillMaxSize())
            }
        }

        // Botón centrado para generar nuevos enlaces
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    waifuViewModel.generateImages()  // Llama a la función que genera los 10 nuevos links
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Generar Nuevas Waifus")
            }
        }
    }
}

@Composable
fun ImageItem(url: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Image URL: $url")
        Spacer(modifier = Modifier.height(8.dp))
    }
}
