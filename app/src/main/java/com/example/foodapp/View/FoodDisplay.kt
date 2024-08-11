package com.example.foodapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodapp.View.SearchFood
import com.example.foodapp.View.ShowFoodList
import com.example.foodapp.ViewModel.FoodViewModel
import com.example.foodapp.data.foodList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    viewModel: FoodViewModel
){
    var foodSearched by remember {
        mutableStateOf("")
    }
    val filteredList = foodList.filter {
        it.tittle.contains(foodSearched, ignoreCase = true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Food Dino",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.white)
                )
            },
            actions = {
                IconButton(onClick ={
                    navController.navigate("FoodOrder")
                }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
                        contentDescription = "Shopping cart",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .wrapContentWidth()
                    )
                }
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "Menu Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.background)
                        )
                        .padding(start = 12.dp, end = 8.dp)
                        .wrapContentSize(align = Alignment.Center)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                colorResource(id = R.color.background)
            ),
        )
        Surface(
            color = colorResource(id = R.color.background),
        ) {
            SearchFood(
                foodSearch = foodSearched,
                onValueChange = { foodSearched = it },
                placehold = R.string.search_for_foods,
                leadingIcon = R.drawable.baseline_search_24,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 16.dp, start = 12.dp, end = 12.dp)
                    .fillMaxWidth()
            )
        }
        if (foodSearched == "") {
            ShowFoodList(foodList = foodList,
                viewModel = viewModel)
        }
        else {
            ShowFoodList(foodList = filteredList,
                viewModel = viewModel)
        }
    }
}