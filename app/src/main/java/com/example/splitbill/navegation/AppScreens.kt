package com.example.splitbill.navegation

sealed class AppScreens(val route : String){

    object MainPage: AppScreens("main_page")

    object NewBill: AppScreens("new_bill")

    object MainBillA: AppScreens("main_bill_a")

    object MainBillB: AppScreens("main_bill_b")

    object NewExpensesOrTransfer: AppScreens("new_expenses_or_transfer")

}