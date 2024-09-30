package com.example.waifuapi.network

import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://api.waifu.pics/"

interface WaifuApiService {
    // Obtener una imagen de la categoría sfw o nsfw
    @GET("{type}/waifu")
    suspend fun getImage(@Path("type") type: String): WaifuImageResponse
}

object WaifuApi {
    val retrofitService: WaifuApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WaifuApiService::class.java)
    }
}

@Serializable
data class WaifuImageResponse(
    val url: String
)



