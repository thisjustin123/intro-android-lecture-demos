package com.cornellappdev.introandroid.lecturedemos.lec2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp

@Composable
fun SimpleCounterRow() {
    var count by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Count: $count",
            fontSize = 24.sp
        )

        Button(
            onClick = {
                // TODO 1: Update count!
                //  Would this still work if count wasn't a mutableStateOf?
                count += 1
            },
        ) {
            Text(text = "Add")
        }
    }
}

@Preview
@Composable
fun SimpleCounterRowPreview() {
    SimpleCounterRow()
}
