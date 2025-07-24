package com.example.studyhub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.studyhub.screens.home.HomeScreen
import com.example.studyhub.screens.login.LoginScreen
import com.example.studyhub.screens.quizzes.QuizzesScreen
import com.example.studyhub.screens.register.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "quizzes") {
        composable("login") {
            LoginScreen(onCreateAccountClick = {navController.navigate("register")})

        }
        composable("register") {
                RegisterScreen(onBackClick = {navController.popBackStack()})
        }
        composable("home") {
            HomeScreen(userName = "Marcos")
        }
        composable("quizzes") {
            QuizzesScreen()
        }
    }
}