package com.example.studyhub.screens.provas

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyhub.ui.theme.StudyhubTheme

data class Prova(
    val disciplina: String,
    val avaliacao: String,
    val semestre: String,
    val professor: String,
    val arquivoUrl: String
)

@Composable
fun ProvasAnterioresScreen(
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current

    val semestres = listOf("2025.1", "2024.2", "2024.1", "2023.2")
    var semestreSelecionado by remember { mutableStateOf("2025.1") }

    val provas = remember(semestreSelecionado) {
        listOf(
            Prova("Cálculo I", "Primeira Avaliação", "2025.1", "Prof. Silva", "https://meusite.com/calculo1.pdf"),
            Prova("Física I", "Segunda Avaliação", "2025.1", "Prof. Santos", "https://meusite.com/fisica1.pdf"),
            Prova("Programação", "Prova Final", "2025.1", "Prof. Oliveira", "https://meusite.com/programacao.pdf")
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
            Text("Provas Anteriores", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.Download, contentDescription = "Filtro")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Semestre filter
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            semestres.forEach { semestre ->
                FilterChip(
                    selected = semestre == semestreSelecionado,
                    onClick = { semestreSelecionado = semestre },
                    label = { Text(semestre) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de provas
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(provas) { prova ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(prova.disciplina, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text("${prova.avaliacao} - ${prova.semestre}")
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(prova.professor, color = MaterialTheme.colorScheme.primary)
                            IconButton(onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(prova.arquivoUrl))
                                context.startActivity(intent)
                            }) {
                                Icon(Icons.Default.Download, contentDescription = "Baixar")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProvasAnterioresScreen() {
    StudyhubTheme {
        ProvasAnterioresScreen()
    }
}