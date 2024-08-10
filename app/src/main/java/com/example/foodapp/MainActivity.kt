@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import com.example.foodapp.Navigation.NavPath
import com.example.foodapp.ui.theme.FoodAppTheme
import com.example.foodapp.View.MainPage
import com.example.foodapp.View.ViewFoods

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
                NavPath()
            }
        }
    }
}

