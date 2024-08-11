package com.example.foodapp.View

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.foodapp.ViewModel.FoodViewModel
import com.example.foodapp.data.Foods
import com.example.foodapp.ui.theme.FoodAppTheme
import kotlinx.coroutines.delay

@Composable
fun ShowFoodList(foodList: List<Foods>,
                 viewModel: FoodViewModel) {
    LazyColumn {
        items(foodList) { food ->
            FoodCard(food, Modifier.padding(16.dp),viewModel)
        }
    }
}
@Composable
fun FoodCard(
    food: Foods,
    modifier: Modifier,
    viewModel: FoodViewModel
             ) {
    val uiState by viewModel.uiState.collectAsState()
    var isExpanded by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        modifier = modifier
            .clickable {
                isExpanded = !isExpanded
            }
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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                Text(
                    text = food.tittle,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.textcolor),
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .weight(2f)
                )
                IconButton(
                        onClick = {
                            viewModel.addFoodToOrder(food)
                           // println("Current Total Price: ${uiState.totalPrice}")
                        }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                        contentDescription = "Add to cart",
                        tint = colorResource(id = R.color.background),
                        modifier = Modifier.size(30.dp)
                    )

                }
                }
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column {
                        Text(
                            text = food.description,
                            style = MaterialTheme.typography.titleMedium,
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
@Composable
fun  MainPage(
    navController:NavController
){
    var showFrontPage by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        delay(1000)
        showFrontPage = false
    }
    if (showFrontPage) {
        FrontPages()
    } else {
       navController.navigate("Login")
        }
    }
@Preview
@Composable
fun DisplayApp() {
    FoodAppTheme {
        NavPath()
    }
}
