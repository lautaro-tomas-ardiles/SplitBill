package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.splitbill.R
import com.example.splitbill.data.DataOfExpenses
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*

val dataExpense = mutableListOf<DataOfExpenses>()
val expensesAndParticipants: MutableList<MutableList<Any?>> = mutableListOf()
var allExpenses = 0.0

fun allExpenses(){
    var expenses = 0.0
    for (element in dataExpense) {
        if (!element.isTransfer && element.id == actualIdSelection) {
            expenses += element.amount
            expensesAndParticipants.add(mutableListOf(element.amount,element.paidBy))
            allExpenses = expenses
        }
    }
}

@Composable
fun MainBillATopBar(navController: NavController){
    TopAppBar (
        backgroundColor = DarkBackgroundColor,
        modifier = Modifier
            .height(132.dp)
            .drawBehind {
                drawLine(
                    color = OrangeBorderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 6f,
                    cap = StrokeCap.Round
                )
            }

    ) {
        Column {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrow back",
                    tint = DarkTextColor,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(route = AppScreens.MainPage.route)
                        }
                        .padding(20.dp)
                        .width(24.dp)
                )
                Column {
                    Text(
                        text = titleSelection,
                        fontSize = 20.sp,
                        color = DarkTextColor
                    )
                    Text(
                        text = participantsSelection,
                        fontSize = 20.sp,
                        color = DarkTextColor
                    )
                }
            }

            Row {
                Column (Modifier.padding(start = 35.dp)){
                    Icon(
                        painter = painterResource(R.drawable.validando_ticket),
                        contentDescription = "bill",
                        tint = DarkTextColor,
                         modifier = Modifier
                             .padding(start = 10.dp)
                    )

                    Text(
                        text = "Expenses",
                        fontSize = 16.sp,
                        color = DarkTextColor,

                    )
                }
                    Spacer(Modifier.padding(horizontal = 94.dp))
                Column (
                    Modifier.clickable {
                        navController.navigate(route = AppScreens.MainBillB.route)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.group_2),
                        contentDescription = "balances",
                        tint = DarkTextColor,
                        modifier = Modifier
                            .padding(start = 10.dp)
                    )
                        Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = "balances",
                        fontSize = 16.sp,
                        color = DarkTextColor
                    )
                }
            }
        }
    }
}

@Composable
fun MainBillABottomBar(navController: NavController){
    BottomAppBar( backgroundColor = DarkBackgroundColor ) {

        Image(
            painter = painterResource(R.drawable.mas),
            contentDescription = "add",
            modifier = Modifier
                .size(75.dp)
                .clickable {
                    navController.navigate(route = AppScreens.NewExpensesOrTransfer.route)
                }
                .padding(vertical = 2.dp)
                //.absoluteOffset {}
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Column {
            Text(
                text = "Total Expenses",
                fontSize = 18.sp,
                color = DarkTextColor
            )
            Text(
                text = "$$allExpenses",
                fontSize = 18.sp,
                color = DarkTextColor,
                modifier = Modifier.padding(start = 50.dp)
            )
        }
    }
}

@Composable
fun MainBillA(navController: NavController){
    Scaffold (
        topBar = {
            MainBillATopBar(navController)
        }
    ) {
        Scaffold (
            bottomBar = {
                MainBillABottomBar(navController)
            }
        ) {
            allExpenses()
            ExpenseData(dataExpense)
        }
    }
}

@Composable
fun ExpenseData(elements: List<DataOfExpenses>){
    LazyColumn {
        items (elements) { elements ->
            if (elements.id == actualIdSelection) {
                ComponentExpense(elements)
            }
        }
    }
}

@Composable
fun ComponentExpense(elements: DataOfExpenses){
    Row(Modifier
        .background(color = DarkSubBackgroundColor)
        .drawBehind {
            drawLine(
                color = DarkBorderColor,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 6f,
                cap = StrokeCap.Round
            )
        }
        .padding(13.dp)
        .fillMaxWidth()
    ) {
        TextsOfExpense(elements)
    }
}

@Composable
fun TextsOfExpense(elements: DataOfExpenses){
    Row{
        Column {
            Text(
                if (elements.isTransfer) "transference" else "expenses",
                color = DarkTextColor,
                style = MaterialTheme.typography.subtitle1
            )
                Spacer(modifier = Modifier.padding(5.dp))
            Text(
                elements.title,
                color = DarkTextColor,
                style = MaterialTheme.typography.subtitle2
            )
        }
            Spacer(modifier = Modifier.padding(horizontal = 50.dp))
        Column {
            Text(
                "$${elements.amount}",
                color = DarkTextColor,
                style = MaterialTheme.typography.subtitle1
            )
                Spacer(modifier = Modifier.padding(5.dp))
            Text(
                elements.date,
                color = DarkTextColor,
                style = MaterialTheme.typography.subtitle2
            )
            Text("${elements.id}")

        }
    }
}