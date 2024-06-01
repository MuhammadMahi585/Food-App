package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.foodapp.ui.theme.FoodAppTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartActivity2 : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonString = intent.getStringExtra("list")
        val listType = object : TypeToken<List<Foods>>() {}.type
        val list = Gson().fromJson<List<Foods>>(jsonString, listType)
        val list2: List<Foods> = list ?: emptyList()
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Food Dino",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = colorResource(id = R.color.white)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        colorResource(id = R.color.background)),
                )}
                Surface {
                }
        }
    }
}
}