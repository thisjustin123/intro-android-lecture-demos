package com.cornellappdev.introandroid.lecturedemos.lec1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import kotlin.math.absoluteValue

/**
 * An expense row for a list of expenses.
 *
 * Takes in an expense title, date, and amount.
 *
 * Displays the title, date underneath, and amount to the far right;
 * if the amount is negative, it is in red, and if it is positive, it is in green.
 */
@Composable
fun ExpenseRow(
    title: String,
    date: String,
    amount: Double? = null,
) {
    // 2.0 -> $2.00
    val formattedAmount = String.format(Locale.US, "%.2f", amount?.absoluteValue)
    val amountText =
        if (amount != null && amount < 0) {
            "-$$formattedAmount"
        } else {
            "+$$formattedAmount"
        }

    // TODO: Use modifiers and arguments to make this look nice!
    //  * (Anything else...?)
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Text(
                text = date
            )
        }

        Spacer(Modifier.weight(1f))

        if (amount != null) {
            Text(
                text = amountText,
                color = if (amount < 0) {
                    Color.Red
                } else {
                    Color.Green
                },
                )
        }
    }
}

@Preview
@Composable
fun ExpenseRowPreview() {
    Column {
        ExpenseRow(
            title = "Groceries",
            date = "2022-01-01",
            amount = -100.0
        )
        ExpenseRow(
            title = "Groceries",
            date = "2022-01-01",
            amount = +100.0
        )
        ExpenseRow(
            title = "Groceries",
            date = "2022-01-01"
        )
    }
}
