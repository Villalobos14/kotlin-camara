package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.*
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.ProductListScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController= rememberNavController()
            NavHost(navController=navController, startDestination = "login"){
                composable("login"){
                    LoginScreen(onLoginSuccess = {navController.navigate("productList")})
                }
                composable("productList"){
                    ProductListScreen()
                }
            }

        }
    }
}


