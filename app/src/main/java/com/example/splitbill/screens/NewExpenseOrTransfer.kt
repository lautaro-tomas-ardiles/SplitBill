package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.splitbill.data.DataOfExpenses
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*

private var titleOfExpenseOrTransfer = ""
private var void = false
private var amountOfExpenseOrTransfer = 0.0
private var paidByOfExpenseOrTransfer = ""
private var isTransferOfExpenseOrTransfer = false

@Composable
fun NewExpensesOrTransferTopBar(navController: NavController){
    TopAppBar (backgroundColor = DarkBackgroundColor) {
        IconButton(
            modifier = Modifier.size(70.dp),
            onClick = {
                navController.navigate(route = AppScreens.MainBillA.route)
            }){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow back",
                tint = DarkTextColor
            )
        }
        Text(
            text = "New expenses",
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
                    if (!void) {
                        dataExpense.add(
                            DataOfExpenses(
                                title = titleOfExpenseOrTransfer,
                                amount = amountOfExpenseOrTransfer,
                                paidBy = paidByOfExpenseOrTransfer,
                                isTransfer = isTransferOfExpenseOrTransfer,
                                id = actualIdSelection
                            )
                        )
                            navController.navigate(route = AppScreens.MainBillA.route)
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
fun NewExpensesOrTransfer(navController: NavController){
    Scaffold (
        topBar = {
            NewExpensesOrTransferTopBar(navController)
        } ) {
        val scrollState2 = rememberScrollState()
        Column( modifier = Modifier.verticalScroll(state = scrollState2)) {
            TextFieldsOfExpensesOrTransfer()
        }
    }
}

@Composable
fun TextFieldsOfExpensesOrTransfer(){
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var paidBy by remember { mutableStateOf("") }
    var showDropdownMenu by remember { mutableStateOf(false) }
    var isTransfer by remember { mutableStateOf(false) }

    Column(Modifier
            .padding(vertical = 23.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = title,
            onValueChange = { title = it } ,
            singleLine = true,
            label = { Text("title") }
        )
        Spacer(modifier = Modifier.padding(21.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it } ,
            singleLine = true,
            label = { Text("amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.padding(21.dp))

        OutlinedTextField(
            value = paidBy,
            onValueChange = { paidBy = it } ,
            singleLine = true,
            label = { Text("paid by") }
        )

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            "Expenses or transfer "
        )
            Spacer(modifier = Modifier.padding(3.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown Icon",
            tint = DarkTextColor,
            modifier = Modifier
                .clickable {
                    showDropdownMenu = true
                }
            )
        DropdownMenu(
            expanded = showDropdownMenu,
            onDismissRequest = { showDropdownMenu = false },
            offset = DpOffset(100.dp,0.dp)
        ) {
            DropdownMenuItem(
                onClick = {
                    isTransfer = true
                    showDropdownMenu = false
                }
            ) {
                Text(text = "transfer",
                    textAlign = TextAlign.Center)
            }
            DropdownMenuItem(
                onClick = {
                    isTransfer = false
                    showDropdownMenu = false
                }
            ) {
                Text("Expense")
            }
        }
    }

    titleOfExpenseOrTransfer = title
    amountOfExpenseOrTransfer = amount.toDoubleOrNull() ?: 0.0
    paidByOfExpenseOrTransfer = paidBy
    isTransferOfExpenseOrTransfer = isTransfer
    void = titleOfExpenseOrTransfer.isBlank() || amountOfExpenseOrTransfer == 0.0 || paidByOfExpenseOrTransfer.isBlank()

}
