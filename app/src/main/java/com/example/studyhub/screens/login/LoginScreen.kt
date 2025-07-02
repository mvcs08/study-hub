package com.example.studyhub.screens.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyhub.ui.theme.StudyhubTheme

@Composable
fun LoginScreen(onCreateAccountClick: () -> Unit) {
    Surface(color = Color.White){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.School,
                contentDescription = "Study Hub",
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFFF0F0F0), shape = CircleShape)
                    .padding(16.dp),
                tint = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "StudyHub",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A1A1A),
                    contentColor = Color.White
                )
            ) {

                Icon(Icons.Default.Email, contentDescription = "Email Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Entrar com e-mail")

            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = {/* login institucional */},
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.AccountBalance, contentDescription = "Institucional Icon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Entrar com conta institucional")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Links

            TextButton(onClick = onCreateAccountClick ) {
                Text("Criar conta")
            }
            HorizontalDivider(thickness = 1.dp)
            TextButton(onClick = {}) {
                Text("Esqueceu a senha?")
            }
        }
    }
}

@Composable
@Preview
fun LoginPreview(){
    StudyhubTheme {
        LoginScreen(onCreateAccountClick = {})
    }
}