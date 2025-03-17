package com.cornellappdev.introandroid.lecturedemos.lec2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderEntry(
    onClick: (title: String, importance: Importance) -> Unit,
) {
    // TODO 2: Add an onClick!
    val sliderState = remember {
        SliderState(steps = 1, valueRange = 0f..2f)
    }

    var typedText by remember {
        mutableStateOf("")
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
                value = typedText,
                onValueChange = {
                    typedText = it
                },
                placeholder = {
                    Text(text = "Enter Title Here...")
                }
            )

            Button(
                onClick = {
                    val importance = when (sliderState.value) {
                        0f -> Importance.LOW
                        1f -> Importance.MEDIUM
                        else -> Importance.HIGH
                    }
                    onClick(typedText, importance)
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
private fun ReminderEntryPreview() = Column {
    // (Solution to 2)
//    var reminder by remember {
//        mutableStateOf<Reminder?>(null)
//    }

    var reminders by remember {
        mutableStateOf<List<Reminder>>(emptyList())
    }
    // TODO 2: Add a usage of `ReminderRow`!
    //  If a reminder has been given, render a row.
    //  (If not, render nothing.)
    ReminderEntry { title, importance ->
        val list = reminders + Reminder(title, importance)
        reminders = list
        // (Solution to 2)
//        reminder = Reminder(title, importance)
    }

    // Equivalent to an if not null statement!
    // (Solution to 2)
//    reminder?.let {
//        ReminderRow(title = it.title, importance = it.importance)
//    }

    // TODO 3: Add a lazy column that can render MANY `ReminderRow`s!
    LazyColumn {
        items(reminders) { reminder: Reminder ->
            ReminderRow(title = reminder.title, importance = reminder.importance)
        }
    }
}
