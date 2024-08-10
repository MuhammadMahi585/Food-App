package com.example.foodapp.View

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.foodapp.Navigation.NavPath
import com.example.foodapp.R
import com.example.foodapp.data.Foods
import com.example.foodapp.data.foodList
import com.example.foodapp.data.foodOrder
import com.example.foodapp.ui.theme.FoodAppTheme
import kotlinx.coroutines.delay

@Composable
fun ShowFoodList(foodList: List<Foods>) {
    LazyColumn {
        items(foodList) { food ->
            FoodCard(food, Modifier.padding(16.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodCard(food: Foods, modifier: Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = modifier
            .clickable {
                isExpanded = true//!isExpanded
            }
            .combinedClickable {
                foodOrder.add(food)
            }
        ,



        ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = food.imgResource),
                contentDescription = "Food",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = food.tittle,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column {
                        Text(
                            text = food.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FrontPages() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ai_generated_7925730_1280_removebg_preview),
                contentDescription = "Icon Image",
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = stringResource(R.string.food_dino),
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 44.sp,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun SearchFood(
    foodSearch: String,
    onValueChange: (String) -> Unit,
    @StringRes placehold: Int,
    @DrawableRes leadingIcon: Int,
    modifier: Modifier,
) {
    OutlinedTextField(
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) },
        value = foodSearch,
        onValueChange = onValueChange,
        maxLines = 1,
        shape = CircleShape,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
        ),
        placeholder = { Text(stringResource(id = placehold)) },
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.background),
            unfocusedBorderColor = colorResource(id = R.color.background),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedPlaceholderColor = Color.Black,
            focusedPlaceholderColor = Color.Black
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  MainPage(
    navController:NavController
){
    var showFrontPage by remember {
        mutableStateOf(true)
    }
    var foodSearched by remember {
        mutableStateOf("")
    }
    val filteredList = foodList.filter {
        it.tittle.contains(foodSearched, ignoreCase = true)
    }
    LaunchedEffect(Unit) {
        delay(1000)
        showFrontPage = false
    }
    if (showFrontPage) {
        FrontPages()
    } else {
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
                ShowFoodList(foodList = foodList)
                }
            else {
                ShowFoodList(foodList = filteredList)
                }
            }
        }
    }
@Preview
@Composable
fun DisplayApp() {
    FoodAppTheme {
        NavPath()
    }
}
