package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.splitbill.data.DataOfBill
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*

private var idCounter = 0
private var isVoid = false
private var titleOfBill = ""
private var descriptionOfBill = ""
private var selectedBadgeOfBill = ""
private val participantsOfBill = mutableListOf<String>()

@Composable
fun NewBillTopBar(navController: NavController){
    TopAppBar (
        backgroundColor = DarkBackgroundColor
    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = "Arrow back",
            tint = DarkTextColor,
            modifier = Modifier
                .clickable { navController.navigate(route = AppScreens.MainPage.route) }
                .padding(10.dp)
        )
        Text(
            text = "New bill",
            fontSize = 24.sp,
            color = DarkTextColor
        )
        Spacer(modifier = Modifier.padding(100.dp))

        Icon(imageVector = Icons.Default.Check,
            contentDescription = "chek",
            tint = DarkTextColor,
            modifier = Modifier
                .clickable {
                    if (!isVoid) {
                        billData.add(
                            DataOfBill(
                                title = titleOfBill,
                                description = descriptionOfBill,
                                badge = selectedBadgeOfBill,
                                participants = participantsOfBill.toList(),
                                id = idCounter
                            )
                        )
                        idCounter++
                        participantsOfBill.clear()
                    }
                    navController.navigate(route = AppScreens.MainPage.route)
                }

                .padding(10.dp)
        )

    }
}

@Composable
fun NewBill(navController: NavController){
    Scaffold ( topBar = {
        NewBillTopBar(navController)
    } ) {
        Column {
            TextFieldsOfBill()
            Participants()
        }
    }
}

@Composable
fun TextFieldsOfBill(){
    val badge = listOf("eur","usd","ars")

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedBadge by remember { mutableStateOf(badge[0]) }
    var showDropDownMenu by remember { mutableStateOf(false) }

    Column(Modifier.padding(horizontal = 65.dp, vertical = 23.dp)){
        OutlinedTextField(
            value = title,
            onValueChange = { title = it } ,
            singleLine = true,
            label = { Text("title") }
        )

        Spacer(modifier = Modifier.padding(21.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it } ,
            singleLine = true,
            label = { Text("description") }
        )

        Spacer(modifier = Modifier.padding(21.dp))

        OutlinedTextField(
            value = selectedBadge ,
            onValueChange = { },
            label = {},
            readOnly = true,
            trailingIcon = {
                IconButton(
                    modifier = Modifier.size(width = 120.dp, height = 25.dp),
                    onClick = { showDropDownMenu = true }
                ) {
                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "arrow down"
                    )
                }
            }
        )

        DropdownMenu(
            modifier = Modifier.padding(start = 85.dp),
            expanded = showDropDownMenu,
            onDismissRequest = {showDropDownMenu = false}
        ) {
            badge.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        showDropDownMenu = false
                        selectedBadge = item
                } ) {
                Text(item)
            }
        }
    }
}

    isVoid = title.isBlank() && description.isBlank()
    titleOfBill = title
    descriptionOfBill = description
    selectedBadgeOfBill = selectedBadge
}

@Composable
fun Participants(){
    var participant by remember { mutableStateOf("") }

    Row(Modifier
        .background(DarkSubBackgroundColor)
        .fillMaxWidth()
    ) {
        Text(
            "Participants ( ${participantsOfBill.size} / 50 )",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
        )
    }

    OutlinedTextField(
        value = participant ,
        onValueChange = { participant = it },
        label = { Text("participant") },
        singleLine = true,
        modifier = Modifier.padding(horizontal = 64.dp, vertical = 18.dp),
        trailingIcon = {
            Button(
                modifier = Modifier.padding(end = 5.dp),
                onClick = {
                    participantsOfBill.add(participant)
                    participant = ""
                }
            ) {
                Text("add")
            }
        }
    )

    Column (Modifier.padding(horizontal = 66.dp)) {
        for (x in participantsOfBill){
            Text(
                text = x,
                fontSize = 22.sp
            )
        }
    }
}
