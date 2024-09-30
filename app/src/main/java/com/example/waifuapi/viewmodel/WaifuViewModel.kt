package com.example.waifuapi.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waifuapi.network.WaifuApi
import kotlinx.coroutines.launch

// Definimos una sealed interface para representar los distintos estados de la interfaz
sealed interface WaifuUiState {
    data class Success(val photos: List<String>) : WaifuUiState // Representa el estado de éxito con la lista de URLs
    object Error : WaifuUiState  // Representa un estado de error
    object Loading : WaifuUiState  // Representa el estado de carga mientras se obtienen los datos
}

// Creamos el ViewModel que se encargará de manejar la lógica de negocio y los estados de la UI
class WaifuViewModel : ViewModel() {

    // Definimos el estado actual de la UI, que puede ser Loading, Success o Error
    var waifuUiState: WaifuUiState by mutableStateOf(WaifuUiState.Loading)
        private set // Lo hacemos privado para evitar modificaciones desde fuera de la clase

    // Al inicializar el ViewModel, llamamos automáticamente a la función para obtener las fotos
    init {
        generateImages() // Llamamos a la función al inicializar
    }

    // Esta función se encarga de hacer las llamadas a la API y gestionar los distintos estados
    fun generateImages() {
        viewModelScope.launch {
            // Indicamos que estamos en estado de carga
            waifuUiState = WaifuUiState.Loading
            val urls = mutableListOf<String>()  // Lista temporal para almacenar las URLs
            try {
                // Realizamos 20 peticiones a la API para obtener 10 URLs de imágenes
                for (i in 1..20) {
                    val response = WaifuApi.retrofitService.getImage("sfw") // Petición a la API
                    urls.add(response.url)  // Agregamos la URL a la lista
                }
                // Si las peticiones son exitosas, actualizamos el estado a Success y pasamos la lista de URLs
                waifuUiState = WaifuUiState.Success(urls)
            } catch (e: Exception) {
                // Si ocurre algún error, cambiamos el estado a Error
                waifuUiState = WaifuUiState.Error
            }
        }
    }
}
