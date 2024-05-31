package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.theme.FoodAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
             Column(modifier = Modifier
                 .fillMaxSize()
               ) {
                 TopAppBar(
                     title ={Text(text = "Food Dino",
                         style= MaterialTheme.typography.headlineMedium,
                          fontWeight = FontWeight.SemiBold,
                          color = colorResource(id = R.color.textcolor)
                         )
                 },
                     colors = TopAppBarDefaults.topAppBarColors(
                     colorResource(id = R.color.background)
                     )
                 )
                 ShowFoodList(foodList = foodList)
             }
                }
            }
        }
    }
@Composable
fun ShowFoodList(foodList: List<Foods>){
    LazyColumn {
        items(foodList){
            FoodCard(it, Modifier.padding(16.dp))
        }
    }
}
@Composable
fun FoodCard(foods: Foods, modifier: Modifier){
    var isExpaned by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = modifier.clickable { isExpaned=!isExpaned }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(id = foods.imgResource), contentDescription = "Food",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = foods.tittle, style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 4.dp))
                    AnimatedVisibility(
                        visible = isExpaned,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Column {
                            Text(text = foods.description, style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(bottom = 4.dp))
                        }
                    }
               }
        }
}
}

