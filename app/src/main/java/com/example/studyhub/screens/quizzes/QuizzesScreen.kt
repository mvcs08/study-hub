package com.example.studyhub.screens.quizzes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyhub.ui.theme.StudyhubTheme

@Composable
fun QuizzesScreen() {
    var selectedTab by remember { mutableStateOf("Múltipla Escolha") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        )
        {
            // Back Icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(onClick = { }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")

                }
                Text("Banco de questões", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(24.dp))
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Menu, contentDescription = "Voltar")

                }
            }
        }

        // Dropdown de disciplinas (placeholder)
        var expanded by remember { mutableStateOf(false) }
        var selectedDisciplina by remember { mutableStateOf("Selecione a Disciplina") }

        Box() {
            // CAMPO DE BUSCA
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Selecione a disciplina") },
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                },
                shape = RoundedCornerShape(12.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
//                DropdownMenuItem(
//                    onClick = {
//                        selectedDisciplina = "Matemática"
//                        expanded = false
//                    },
//                ) {
//                    Text("Matemática")
//                }
//                DropdownMenuItem(onClick = {
//                    selectedDisciplina = "Física"
//                    expanded = false
//                }) {
//                    Text("Física")
//                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Botões de tipo de questão
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("Múltipla Escolha", "Discursiva", "Modo Treinamento").forEach { tipo ->
                val isSelected = selectedTab == tipo
                Button(
                    onClick = { selectedTab = tipo },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) Color.Black else Color.LightGray,
                        contentColor = if (isSelected) Color.White else Color.Black
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(text = tipo, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Questão Múltipla Escolha
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Múltipla Escolha", fontWeight = FontWeight.Bold)
                Text(
                    "Qual é a derivada da função f(x) = x² + 3x + 2?",
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(Modifier.height(8.dp))
                listOf("a) 2x + 3", "b) x² + 3", "c) 2x").forEach {
                    Text(text = it, modifier = Modifier.padding(4.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    "Tópico: Derivadas | Taxa de Acerto: 75%",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        // Questão Discursiva
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Discursiva", fontWeight = FontWeight.Bold)
                Text(
                    "Explique o conceito de limite e sua importância no cálculo diferencial.",
                    modifier = Modifier.padding(top = 8.dp)
                )
                Button(
                    onClick = { /* Ação ao clicar em responder */ },
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Text("Responder")
                }
                Spacer(Modifier.height(8.dp))
                Text("Tópico: Limites | Respondida: 234x", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}
