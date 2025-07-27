package com.example.studyhub.screens.register



import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyhub.api.ApiClient
import com.example.studyhub.models.UserRegister
import com.example.studyhub.ui.theme.StudyhubTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(
    onBackClick: () -> Unit = {},
    onCreateAccount: () -> Unit = {},
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var matricula by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    val cursos = listOf("Ciência da Computação", "Licenciatura em Computação", "Sistemas de Informação")
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .fillMaxHeight()
        ) {


        Row(
            modifier = Modifier.fillMaxWidth()
        )
        {
            // Back Icon
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick) { Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")

                }
                Text("Criar Conta", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }


        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text(("Nome Completo")) },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = matricula,
            onValueChange = {matricula = it},
            label = { Text(("Matrícula")) },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownTextField(
            label = "Curso",
            options = cursos,
            selectedOption = course,
            onOptionSelected = {course = it}
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (senhaVisivel)
                    Icons.Default.Visibility
                else Icons.Default.VisibilityOff
                IconButton(onClick = { senhaVisivel = !senhaVisivel}) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        )
        Text("Mínimo de 8 caracteres", fontSize = 12.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (name.isBlank() || matricula.isBlank() || course.isBlank() ||
                    email.isBlank() || password.isBlank()) {
                    errorMessage = "Preencha todos os campos"
                    return@Button
                }

                if (password.length < 8) {
                    errorMessage = "A senha deve ter pelo menos 8 caracteres"
                    return@Button
                }

                coroutineScope.launch {
                    isLoading = true
                    errorMessage = null
                    try {
                        val usuario = UserRegister(
                            name = name,
                            matricula = matricula,
                            course = course,
                            email = email,
                            password = password
                        )

                        val response = ApiClient.api.registrarUsuario(usuario)
                        if (response.isSuccessful) {
                            Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_LONG).show()
                            delay(1500) // opcional: espera 1.5s antes de voltar
                            onCreateAccount()
                        } else {
                            errorMessage = "Erro no registro: ${response.message()}"
                        }
                    } catch (e: Exception) {
                        errorMessage = "Erro de conexão: ${e.message}"
                    } finally {
                        isLoading = false
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Text("Criar conta")
            }
        }

        errorMessage?.let { message ->
            Text(
                text = message,
                color = Color.Red,
                modifier = Modifier.padding(8.dp))
        }
    }

}


@Composable
@Preview(showBackground = true)
fun PreviewRegisterScreen() {
    StudyhubTheme {
        RegisterScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownTextField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onOptionSelected(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}