package com.example.studyhub.models

data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val course: String,
    val matricula: String
)