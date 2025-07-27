package com.example.studyhub.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.studyhub.screens.home.HomeScreen
import com.example.studyhub.screens.login.EmailLoginScreen
import com.example.studyhub.screens.login.LoginScreen
import com.example.studyhub.screens.provas.ProvasAnterioresScreen
import com.example.studyhub.screens.register.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onCreateAccountClick = {navController.navigate("register")},
                onEmailLoginClick = {navController.navigate("email_login")}
                )

        }
        composable("email_login") {
            EmailLoginScreen(
                onLoginSuccess = { token -> navController.navigate("home") },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("register") {
                RegisterScreen(
                    onBackClick = {navController.popBackStack()},
                    onCreateAccount = {navController.popBackStack()}
                )
        }
        composable("home") {
            HomeScreen(userName = "Marcos",
                onNavigateToProvas = {
                    navController.navigate("provas")
                })
        }
        composable("provas") {
            ProvasAnterioresScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}