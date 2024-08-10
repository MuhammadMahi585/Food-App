package com.example.foodapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.View.LoginPage
import com.example.foodapp.View.MainPage
import com.example.foodapp.View.SignUp
import com.example.foodapp.View.ViewFoods
import com.example.foodapp.ui.theme.FoodAppTheme
import kotlinx.serialization.Serializable


@Composable
fun NavPath(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "MainPage") {
        composable("MainPage") {
            MainPage(navController)
        }
        composable("FoodOrder"){
             ViewFoods()
        }
        composable("SignUp"){
            SignUp()
        }
        composable("Login"){
            LoginPage()
        }
    }
}
@Preview
@Composable
fun ViewApp(){
    FoodAppTheme {
        NavPath()
    }
}