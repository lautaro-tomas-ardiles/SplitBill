package com.example.splitbill.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.splitbill.data.DataOfExpenses
import com.example.splitbill.navegation.AppScreens
import com.example.splitbill.ui.theme.*

private var titleOfExpenseOrTransfer = ""
private var void = false
private var amountOfExpenseOrTransfer = 0.0
private var dateOfExpenseOrTransfer = ""
private var paidByOfExpenseOrTransfer = ""
private var isTransferOfExpenseOrTransfer = false

@Composable
fun NewExpensesOrTransferTopBar(navController: NavController){
    TopAppBar(
        backgroundColor = DarkBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "ArrowBack",
            tint = DarkTextColor,
            modifier = Modifier
                .clickable { navController.navigate(route = AppScreens.MainBillA.route) }
                .padding(10.dp)
        )

        Text(
            text = "new expense",
            fontSize = 24.sp,
            color = DarkTextColor,
            modifier = Modifier
                .padding(start = 7.dp)
        )

        Spacer(modifier = Modifier.padding(70.dp))

        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Check",
            tint = DarkTextColor,
            modifier = Modifier
                .clickable {
                    if (!void){
                        dataExpense.add(
                            DataOfExpenses(
                                title = titleOfExpenseOrTransfer,
                                amount = amountOfExpenseOrTransfer,
                                date = dateOfExpenseOrTransfer,
                                paidBy = paidByOfExpenseOrTransfer,
                                isTransfer = isTransferOfExpenseOrTransfer,
                                id = actualIdSelection
                            )
                        )
                    }
                    navController.navigate(route = AppScreens.MainBillA.route)
                }
                .padding(10.dp)
        )
    }
}

@Composable
fun NewExpensesOrTransfer(navController: NavController){
    Scaffold (
        topBar = {
            NewExpensesOrTransferTopBar(navController)
        } ) {
        Column {
            TextFieldsOfExpensesOrTransfer()
        }
    }
}

@Composable
fun TextFieldsOfExpensesOrTransfer(){
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var paidBy by remember { mutableStateOf("") }
    var showDropdownMenu by remember { mutableStateOf(false) }
    var isTransfer by remember { mutableStateOf(false) }

    Column(Modifier.padding(horizontal = 65.dp, vertical = 12.dp)) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it } ,
            singleLine = true,
            label = { Text("title") }
        )

        Spacer(modifier = Modifier.padding(11.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it } ,
            singleLine = true,
            label = { Text("amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.padding(11.dp))

        OutlinedTextField(
            value = date,
            onValueChange = { date = it } ,
            singleLine = true,
            label = { Text("date (yyyy/MM/dd)") }
        )

        Spacer(modifier = Modifier.padding(11.dp))

        OutlinedTextField(
            value = paidBy,
            onValueChange = { paidBy = it } ,
            singleLine = true,
            label = { Text("paid by") }
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Row (Modifier.padding(start = 83.dp)) {
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
        }
        DropdownMenu(
            expanded = showDropdownMenu,
            onDismissRequest = { showDropdownMenu = false }
        ) {

            DropdownMenuItem(
                onClick = {
                    isTransfer = true
                    showDropdownMenu = false
                }
            ) {
                Text("transfer")
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
    dateOfExpenseOrTransfer = date
    paidByOfExpenseOrTransfer = paidBy
    isTransferOfExpenseOrTransfer = isTransfer
    void = titleOfExpenseOrTransfer.isBlank() || amountOfExpenseOrTransfer == 0.0
            || dateOfExpenseOrTransfer.isBlank() || paidByOfExpenseOrTransfer.isBlank()

}
