package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*

fun balance(expensesAndWhoPaid: List<List<Any?>>, allExpenses: Double, participants: List<String>){
    var expensePerPerson = participants.size / allExpenses


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
                    Modifier.clickable {
                        navController.navigate(route = AppScreens.MainBillA.route) }
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
            ComponentBalance()
        }
    }
}

@Composable
fun ComponentBalance(){
    Column(Modifier
        .padding(vertical = 15.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier
            .border(
                width = 1.dp,
                color = DarkBorderColor,
                shape = RoundedCornerShape(size = 37.dp))
            .width(300.dp)
            .height(111.dp)
            .background(
                color = DarkSubBackgroundColor,
                shape = RoundedCornerShape(size = 37.dp)
            )
        ) {
            TextsMainBillB()
        }
    }
}

@Composable
fun TextsMainBillB(){
    Row {
        Column(Modifier.padding(top = 10.dp)) {
            Text(
                "Carlos",
                color = DarkTextColor,
                fontSize = 17.sp ,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            )

            Text(
                "owes to",
                color = DarkTextColor,
                fontSize = 17.sp ,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            )

            Text(
                "Juan",
                color = DarkTextColor,
                fontSize = 17.sp ,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            )
        }
            Spacer(modifier = Modifier.padding(73.dp))
        Text(
            "$0",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 40.dp)
        )

    }
}