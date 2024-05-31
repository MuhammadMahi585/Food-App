package com.example.foodapp

import androidx.annotation.DrawableRes

data class Foods(
    @DrawableRes val imgResource : Int,
    val tittle : String,
    val description: String
)
var foodList = listOf(
    Foods(R.drawable.burger,"Veg Burger","Burger starting from Rs 80"),
    Foods(R.drawable.fries,"French Fries","Get fries at just 100"),
    Foods(R.drawable.pulao,"Karachi Pulao","Only in Rs 500 per plate"),
    Foods(R.drawable.biryani,"Special Biryani","opening price just 1000"),
    Foods(R.drawable.chow_mein,"Chicken Chow mein","Buy 2 get 1 free"),
    Foods(R.drawable.nuggets,"Nuggets","Dozen in just Rs 2000"),
    Foods(R.drawable.spaghetti,"Spaghetti","Get 20% Off")
)