package com.cornellappdev.introandroid.lecturedemos.lec1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
    amount: Double
) {
    // 2.0 -> $2.00
    val formattedAmount = String.format(Locale.US, "%.2f", amount.absoluteValue)
    val amountText =
        if (amount < 0) {
            "-$$formattedAmount"
        } else {
            "+$$formattedAmount"
        }

    // TODO: Use modifiers and arguments to make this look nice!
    //  * Make the background White!
    //  * Make the title text bold and larger!
    //  * Make everything vertically centered!
    //  * Make the row take up the full width!
    //  * Add padding to make it look nice!
    //  * (Anything else...?)
    Row {
        Column {
            Text(
                text = title
            )
            Text(
                text = date
            )
        }

        Text(
            text = amountText,
            color = if (amount < 0) {
                Color.Red
            } else {
                Color.Green
            }
        )
    }
}

@Preview
@Composable
fun ExpenseRowPreview() {
    ExpenseRow(
        title = "Groceries",
        date = "2022-01-01",
        amount = 100.0
    )
}
