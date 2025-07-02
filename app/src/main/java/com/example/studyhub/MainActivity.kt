package com.example.studyhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.studyhub.navigation.AppNavigation
import com.example.studyhub.ui.theme.StudyhubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudyhubTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}