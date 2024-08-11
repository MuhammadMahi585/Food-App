package com.example.foodapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.foodapp.data.FoodUIState
import com.example.foodapp.data.Foods

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FoodViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(FoodUIState())
    val uiState : StateFlow<FoodUIState> = _uiState.asStateFlow()

    private val _foodOrder = MutableStateFlow<List<Foods>>(emptyList())
    val foodOrder: StateFlow<List<Foods>> = _foodOrder.asStateFlow()

    fun updateTotalValue(tAmount:Int){
        //println("Updating total value by ${_uiState.value.totalPrice}") // Debugging line
        _uiState.update { currentState ->
            currentState.copy(totalPrice = currentState.totalPrice + tAmount)
        }
    }
    fun deleteItem(tAmount: Int){
        _uiState.update { currentState ->
            currentState.copy(totalPrice = currentState.totalPrice - tAmount)
        }
    }
    fun addFoodToOrder(food: Foods) {
        _foodOrder.update { currentOrder ->
            currentOrder + food
        }
        updateTotalValue(food.price) // Update total when a food is added
    }
    fun removeFoodFromOrder(food: Foods) {
        _foodOrder.update { currentOrder ->
            currentOrder - food
        }
        deleteItem(food.price) // Update total when a food is removed
    }

    fun getTotal():Int{
        return uiState.value.totalPrice
    }
}