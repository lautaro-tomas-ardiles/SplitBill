package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.splitbill.R
import com.example.splitbill.data.DataOfBalance
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*
import kotlin.math.abs

val balanceData = mutableListOf<DataOfBalance>()

fun balance(expensesAndWhoPaid: MutableList<MutableList<Any?>>, allExpenses: Double, participants: List<String>){
    val expensePerPerson = participants.size.toDouble() / allExpenses

    for (i in expensesAndWhoPaid.indices){
        if (expensesAndWhoPaid[i][0] is Double){

            val actualValue = expensesAndWhoPaid[i][0] as Double
            expensesAndWhoPaid[i][0] = actualValue - expensePerPerson

            if ((actualValue - expensePerPerson) < 0){
                val absValue:Double = abs(actualValue - expensePerPerson)
                balanceData.add(
                    DataOfBalance(
                        amount = absValue,
                        who = expensesAndWhoPaid[i][1] as String,
                        isDebt = true,
                        id = actualIdSelection
                    )
                )
            }
            if ((actualValue - expensePerPerson) > 0){
                balanceData.add(
                    DataOfBalance(
                        amount = expensesAndWhoPaid[i][0] as Double,
                        who = expensesAndWhoPaid[i][1] as String,
                        isDebt = false,
                        id = actualIdSelection
                    )
                )
            }
        }
    }
}

@Composable
fun MainBillBTopBar(navController: NavController) {
    TopAppBar (
        backgroundColor = DarkBackgroundColor,
        modifier = Modifier
            .height(140.dp)
            .drawBehind {
                drawLine(
                    color = OrangeBorderColor,
                    start = Offset(size.width / 2, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 6f,
                    cap = StrokeCap.Round
                )
            }
    ) {
        Column {
            Row {
                IconButton(
                    modifier = Modifier.size(70.dp),
                    onClick = {
                        navController.navigate(route = AppScreens.MainPage.route)
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow back",
                        tint = DarkTextColor
                    )
                }
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
            Row{
                Column (
                    Modifier
                        .clickable {
                            navController.navigate(route = AppScreens.MainBillA.route)
                        }
                        .padding(start = 35.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.validando_ticket),
                        contentDescription = "bill",
                        tint = DarkTextColor
                    )
                    Text(
                        text = "Expenses",
                        fontSize = 16.sp,
                        color = DarkTextColor
                    )
                }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Column (
                        Modifier.padding(end = 35.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.group_2),
                            contentDescription = "balances",
                            tint = DarkTextColor
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
}

@Composable
fun MainBillBBottomBar(){
    BottomAppBar(backgroundColor = DarkBackgroundColor) {
        Column (
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total Expenses",
                    fontSize = 18.sp,
                    color = DarkTextColor
                )
                Text(
                    text = "$$allExpenses",
                    fontSize = 18.sp,
                    color = DarkTextColor
                )
            }
        }
    }
}

@Composable
fun MainBillB(navController: NavController) {
    Scaffold (
        topBar = {
            MainBillBTopBar(navController)
        }
    ) {
        Scaffold (
            bottomBar = {
                MainBillBBottomBar()
            }
        ) {
            BalanceData(balanceData)
        }
    }
}

@Composable
fun BalanceData(elements: List<DataOfBalance>){
    LazyColumn {
        items (elements) { elements ->
            if (elements.id == actualIdSelection) {
                ComponentBalance(elements)
            }
        }
    }
}

@Composable
fun ComponentBalance(elements: DataOfBalance){
    Column(Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier
                .border(
                    width = 1.dp,
                    color = DarkBorderColor,
                    shape = RoundedCornerShape(size = 37.dp)
                )
                .width(300.dp)
                .height(111.dp)
                .background(
                    color = DarkSubBackgroundColor,
                    shape = RoundedCornerShape(size = 37.dp)
                )
        ) {
            TextsMainBillB(elements)
        }
    }
}

@Composable
fun TextsMainBillB(elements: DataOfBalance){
    if (elements.isDebt){
        Text(
            text = "${elements.who} owes ${elements.amount}",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
    if(!elements.isDebt) {
        Text(
            text = "${elements.who} owe him ${elements.amount}",
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}