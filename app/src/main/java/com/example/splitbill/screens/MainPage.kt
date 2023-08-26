package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.splitbill.R
import com.example.splitbill.data.DataOfBill
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*

val billData = mutableListOf<DataOfBill>()
var titleSelection: String = ""
var participantsSelection: String = ""

@Composable
fun MainPageTopBar(){
    TopAppBar(
        backgroundColor = DarkBackgroundColor
    ) {
        Text(
            text = "Split of bill",
            fontSize = 24.sp,
            color = DarkTextColor,
            modifier = Modifier
                .padding(start = 27.dp)
        )
    }
}

@Composable
fun MainPageBottomBar(navController: NavController){
    BottomAppBar(
        backgroundColor = DarkBackgroundColor
    ) {
        Image(
            painter = painterResource(R.drawable.mas),
            contentDescription = "add",
            modifier = Modifier
                .padding(start = 330.dp, top = 2.dp, bottom = 2.dp)
                .size(87.dp)
                .clickable { navController.navigate(AppScreens.NewBill.route) }
        )
    }
}

@Composable
fun MainPage(navController: NavController){
    Scaffold(bottomBar = {
        MainPageBottomBar(navController)
    } ) {
        Scaffold (topBar = {
            MainPageTopBar()
        }
        ) {
            BillData(billData, navController)
        }
    }
}

@Composable
fun BillData(elements: List<DataOfBill>,navController: NavController){
    LazyColumn {
        items (elements) { elements ->
            ComponentOfBill(navController,elements)
        }
    }
}

@Composable
fun ComponentOfBill(navController: NavController, elements: DataOfBill) {
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
        .clickable {
            navController.navigate(route = AppScreens.MainBillA.route)
            titleSelection = elements.title
            participantsSelection = elements.participants.joinToString(", ")
        }
    ) {
        TextsOfBill(elements)
    }
}

@Composable
fun TextsOfBill(elements: DataOfBill){
    Column {
        Text(elements.title,
            color = DarkTextColor,
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(elements.description,
            color = DarkTextColor,
            style = MaterialTheme.typography.subtitle2
        )
    }
}
