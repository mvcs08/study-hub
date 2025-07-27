package com.example.studyhub.api

import com.example.studyhub.models.LoginRequest
import com.example.studyhub.models.UserRegister
import com.example.studyhub.models.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/register")
    suspend fun registrarUsuario(@Body usuario: UserRegister): Response<Unit>

    @POST("api/auth/login")
    suspend fun login(@Body login: LoginRequest): Response<Unit>
    // Adicione outros endpoints conforme necessário

    @GET("api/user/me")
    suspend fun fetchUser(@Header("Authorization") authHeader: String): Response<UserResponseDTO>


}