package com.example.studyhub.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.studyhub.api.ApiClient
import com.example.studyhub.models.UserResponseDTO

@Composable
fun Welcome(token: String) {
    var user by remember { mutableStateOf<UserResponseDTO?>(null) }

    LaunchedEffect(Unit) {
        user = getUserData(token)
    }

    Text(text = user?.name?.let { "Olá, $it" } ?: "Carregando")
}

suspend fun getUserData(token: String): UserResponseDTO? {
    return try {
        val response = ApiClient.api.fetchUser("Bearer $token")
        if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}