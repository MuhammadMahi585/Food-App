package com.example.foodapp

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.ui.theme.FoodAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppTheme {
                var showFrontPage by remember {
                    mutableStateOf(true)
                }
                var foodSearched by remember {
                    mutableStateOf("")
                }
                var FilteredList= foodList.filter {
                    it.tittle.contains(foodSearched,ignoreCase = true)
                }
                LaunchedEffect(Unit) {
                    delay(1000)
                    showFrontPage= false
                }
                if(showFrontPage){
                FrontPages()
                }
                else {
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
                                        color = colorResource(id = R.color.textcolor)
                                    )
                                },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    colorResource(id = R.color.background)
                                ),
                        )
                        Surface(
                            color = colorResource(id = R.color.background),
                        ){
                            SearchFood(
                                foodSearch = foodSearched,
                                onValueChange = {foodSearched= it},
                                label = R.string.search_for_food,
                                leadingIcon = R.drawable.baseline_search_24,
                                modifier = Modifier
                                    .padding(top = 4.dp, bottom = 16.dp, start = 12.dp, end = 12.dp)
                                    .fillMaxWidth(),
                                foodList = foodList
                            )
                        }
                        if(foodSearched==""){
                        ShowFoodList(foodList = foodList)}
                        else{
                            ShowFoodList(foodList = FilteredList)
                        }
                    }
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
        modifier = modifier.clickable { isExpaned= !isExpaned }
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

@Composable
fun FrontPages(){
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
    onValueChange: (String)->Unit,
    @StringRes label :Int,
    @DrawableRes leadingIcon : Int,
    modifier: Modifier,
    foodList: List<Foods>){
    TextField(
        leadingIcon = {Icon(painter = painterResource(id = leadingIcon),null)},
        value = foodSearch,
        onValueChange = onValueChange,
        label = {Text(stringResource(id = label))},
        shape = CircleShape,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
        ),
        modifier = modifier
    )
}
@Preview
@Composable
fun DisplayApp(){
    FrontPages()
}
