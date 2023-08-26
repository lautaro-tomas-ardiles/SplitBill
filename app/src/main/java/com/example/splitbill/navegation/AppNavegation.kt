package com.example.splitbill.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.splitbill.data.DataOfBill
import com.example.splitbill.screens.*
import com.example.splitbill.navegation.AppScreens.*

@Composable
fun AppNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = MainPage.route){
        composable(route = MainPage.route) {
            MainPage(navController)
        }
        composable(route = NewBill.route){
            NewBill(navController)
        }
        composable(route = MainBillA.route ){
            MainBillA(navController)
        }
        composable(route = MainBillB.route ){
            MainBillB(navController)
        }
        composable(route = NewExpensesOrTransfer.route ){
            NewExpensesOrTransfer(navController)
        }
    }
}