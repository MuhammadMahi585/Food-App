package com.example.foodapp.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.mutableStateOf
import com.example.foodapp.R

data class Foods(
    @DrawableRes val imgResource : Int,
    val tittle : String,
    val description: String,
    val price:Int
)
var foodList = listOf(
    Foods(R.drawable.burger,"Veg Burger","Rs 300",300),
    Foods(R.drawable.fries,"French Fries","Rs 100",100),
    Foods(R.drawable.pulao,"Karachi Pulao","Rs 150",150),
    Foods(R.drawable.biryani,"Special Biryani","Rs 1000",1000),
    Foods(R.drawable.chow_mein,"Chicken Chow mein","Rs 800",800),
    Foods(R.drawable.nuggets,"Nuggets"," Rs 700",700),
    Foods(R.drawable.spaghetti,"Spaghetti"," Rs 740",740)
)
