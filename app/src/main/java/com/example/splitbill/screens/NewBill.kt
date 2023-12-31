package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.splitbill.data.DataOfBill
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*

private var idCounter = 0
private var isMatchGeneral = false
private var titleOfBill = ""
private var descriptionOfBill = ""
private var selectedBadgeOfBill = ""
private val participantsOfBill = mutableListOf<String>()

@Composable
fun NewBillTopBar(navController: NavController){
    TopAppBar (backgroundColor = DarkBackgroundColor) {
        IconButton(
            modifier = Modifier.size(70.dp),
            onClick = {
                navController.navigate(route = AppScreens.MainPage.route)
            }){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                tint = DarkTextColor
            )
        }
        Text(
            text = "New bill",
            fontSize = 24.sp,
            color = DarkTextColor
        )
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.size(70.dp),
                onClick = {
                    if (isMatchGeneral) {
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
                        navController.navigate(route = AppScreens.MainPage.route)
                    }
                }){
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "chek",
                    tint = DarkTextColor
                )
            }
        }
    }
}

@Composable
fun NewBill(navController: NavController){
    Scaffold ( topBar = {
        NewBillTopBar(navController)
    } ) {
        val scrollState = rememberScrollState()
        Column( modifier = Modifier.verticalScroll(state = scrollState)) {
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
    var isMatchT by remember { mutableStateOf(false) }
    var isMatchD by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 23.dp)
            .fillMaxWidth()
    ){
        isMatchT = title.length >= 3
        OutlinedTextField(
            value = title,
            onValueChange = { title = it } ,
            singleLine = true,
            label = { Text("title (min 3 characters)") },
            isError = !isMatchT
        )
        Spacer(modifier = Modifier.padding(21.dp))
        isMatchD = description.length >= 5
        OutlinedTextField(
            value = description,
            onValueChange = { description = it } ,
            singleLine = true,
            label = { Text("description (min 5 characters)") },
            isError = !isMatchD
        )
        Spacer(modifier = Modifier.padding(21.dp))
        OutlinedTextField(
            value = selectedBadge ,
            onValueChange = { },
            label = { },
            readOnly = true,
            trailingIcon = {
                IconButton(
                    modifier = Modifier.size(width = 120.dp, height = 25.dp),
                    onClick = { showDropDownMenu = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "arrow down"
                    )
                }
            }
        )
        DropdownMenu(
            offset = DpOffset(200.dp,0.dp),
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
    isMatchGeneral = isMatchT && isMatchD && participantsOfBill.isEmpty()

    titleOfBill = title
    descriptionOfBill = description
    selectedBadgeOfBill = selectedBadge
}

@Composable
fun Participants(){
    var participant by remember { mutableStateOf("") }
    var isEmpty by remember { mutableStateOf(true) }

    Row(Modifier
            .background(DarkSubBackgroundColor)
            .fillMaxWidth()
    ){
        Text(
            "Participants ( ${participantsOfBill.size} / 50 )",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
        )
    }
    isEmpty = participantsOfBill.isEmpty()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = participant,
            onValueChange = { participant = it },
            label = { Text("participant") },
            singleLine = true,
            modifier = Modifier.padding(horizontal = 64.dp, vertical = 18.dp),
            isError = isEmpty,
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
        for (x in participantsOfBill){
            Text(
                text = x,
                fontSize = 22.sp
            )
        }
    }
}
