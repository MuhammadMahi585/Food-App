package com.example.foodapp.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.HomePage
import com.example.foodapp.View.LoginPage
import com.example.foodapp.View.MainPage
import com.example.foodapp.View.SignUp
import com.example.foodapp.View.ViewFoods
import com.example.foodapp.ViewModel.FoodViewModel
import com.example.foodapp.ui.theme.FoodAppTheme


@Composable
fun NavPath(){
    val foodViewModel:FoodViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "MainPage",) {
        composable("MainPage") {
            MainPage(navController)
        }
        composable("Login"){
            LoginPage(navController)
        }
        composable("FoodOrder"){
             ViewFoods(foodViewModel)
        }
        composable("SignUp"){
            SignUp(navController)
        }
        composable("HomePage"){
            HomePage(navController = navController,viewModel = foodViewModel)
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