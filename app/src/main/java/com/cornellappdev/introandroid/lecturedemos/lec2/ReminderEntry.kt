package com.cornellappdev.introandroid.lecturedemos.lec2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderEntry() {
    // TODO 1: Add an onClick!
    val sliderState = remember {
        SliderState(steps = 4, valueRange = 0f..3f)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reminder Importance:",
            modifier = Modifier.fillMaxWidth()
        )

        Slider(
            state = sliderState,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO 1: Use a [String] state variable to store the title!
            TextField(
                value = "",
                onValueChange = {

                },
                placeholder = {
                    Text(text = "Enter Title Here...")
                }
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = "Add")
            }
        }
    }
}

@Preview
@Composable
fun ReminderEntryPreview() {
    // TODO 2: Add a usage of `ReminderRow` to preview!
    ReminderEntry()

    // TODO 3: Add a lazy column that can render MANY `ReminderRow`s!
}
