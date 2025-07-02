package com.example.studyhub.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyhub.ui.theme.StudyhubTheme

@Composable
fun HomeScreen(userName: String = "João") {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // TOPO: Avatar + nome + sino
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Perfil",
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Olá, $userName", fontWeight = FontWeight.Medium)
            }

            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notificações"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // CAMPO DE BUSCA
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar provas ou questões...") },
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CATEGORIAS (CHIPS)
        Text("Categorias", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(3) { index ->
                val categorias = listOf("Todos", "Cálculo I", "Programação")
                AssistChip(
                    onClick = { /* TODO: filtro */ },
                    label = { Text(categorias[index]) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // AÇÕES (CARDS)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeCard(icon = Icons.Default.Assignment, title = "Provas Anteriores", subtitle = "Acesse provas antigas")
                HomeCard(icon = Icons.Default.Storage, title = "Banco de Questões", subtitle = "Pratique exercícios")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                HomeCard(icon = Icons.Default.Star, title = "Favoritos", subtitle = "Itens salvos")
                HomeCard(icon = Icons.Default.Notifications, title = "Avisos", subtitle = "Atualizações")
            }
        }
    }
}

@Composable
fun HomeCard(icon: ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .clickable { /* TODO: Navegar */ },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(28.dp))
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen(){
    StudyhubTheme {
        HomeScreen()
    }
}