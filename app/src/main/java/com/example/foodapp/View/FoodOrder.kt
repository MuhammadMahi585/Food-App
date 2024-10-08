package com.example.foodapp.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.ViewModel.FoodViewModel
import com.example.foodapp.data.Foods
import com.example.foodapp.ui.theme.FoodAppTheme

@Composable
fun ViewFoods(viewModel: FoodViewModel){
    val foodOrder by viewModel.foodOrder.collectAsState()
    Scaffold(
        modifier = Modifier
            .safeContentPadding()

    ){
        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                FoodTopAppBar()
                LazyColumn(contentPadding = it) {
                    items(foodOrder) { foods ->
                        ViewList(
                            food = foods,
                            viewModel=viewModel
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                   // .padding(dimensionResource(id = R.dimen.medium_padding))
            ) {
           DisplayTotal(viewModel = viewModel)
            ButtonsDisplay()
            }
        }
    }
}
@Composable
fun ViewList(
    food: Foods,
    viewModel: FoodViewModel
){
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(4.dp)),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.background)
        ),
        elevation = CardDefaults.cardElevation(8.dp)

    ) {
        Row {
            Column(
                modifier = Modifier.weight(2F)
            ) {
                Text(
                    text = food.tittle,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = food.description,
                    style = MaterialTheme.typography.displayMedium
                )
            }
            Box(
                contentAlignment = Alignment.CenterEnd
            ){
                IconButton(onClick = {
                    viewModel.removeFoodFromOrder(food)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Cancel Icon",
                        tint = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodTopAppBar(){
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
            colorResource(id = R.color.background)
        ),
    )
}
@Composable
fun ButtonsDisplay( ){
    Row {
        OutlinedButton(onClick = {

        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.background)
            ),
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(4.dp))
                .align(alignment = Alignment.Bottom)) {
            Text(text = "Cancel",
                style = MaterialTheme.typography.titleMedium,
            )
        }
        OutlinedButton(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.background)
            ),
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.Bottom)
                .clip(RoundedCornerShape(8.dp))
                .padding(start = 4.dp)
        ) {

            Text(text = "Confirm",
                style = MaterialTheme.typography.titleMedium,
                )
        }
    }
}
@Composable
fun DisplayTotal(viewModel: FoodViewModel){
         val uiState by viewModel.uiState.collectAsState()
    println("Current Total Price: ${uiState.totalPrice}")
        Surface{
            Text(
                text = "Total Amount: $${uiState.totalPrice}",
                style = MaterialTheme.typography.displaySmall,
                color = colorResource(id = R.color.textcolor),
                modifier = Modifier,
                maxLines = 1
            )
        }
    }

@Preview
@Composable
fun View(){
    FoodAppTheme {
     //   ViewFoods()
    }
}
