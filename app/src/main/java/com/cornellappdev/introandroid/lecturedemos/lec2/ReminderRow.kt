package com.cornellappdev.introandroid.lecturedemos.lec2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * An expense row for a list of expenses.
 *
 * Takes in an expense title, date, and amount.
 *
 * Displays the title, date underneath, and amount to the far right;
 * if the amount is negative, it is in red, and if it is positive, it is in green.
 */
@Composable
fun ReminderRow(
    title: String,
    importance: Importance
) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 24.sp
        )

        Text(
            text = importance.text,
            color = importance.color,
        )
    }
}

@Preview
@Composable
private fun ReminderRowPreview() {
    Column {
        ReminderRow("Reminder", Importance.HIGH)
        ReminderRow("Low Reminder", Importance.LOW)
        ReminderRow("Medium Reminder", Importance.MEDIUM)
    }
}
