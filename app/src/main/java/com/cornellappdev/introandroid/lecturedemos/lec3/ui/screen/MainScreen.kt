package com.cornellappdev.introandroid.lecturedemos.lec3.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cornellappdev.introandroid.lecturedemos.lec3.ui.component.ExpandableList
import com.cornellappdev.introandroid.lecturedemos.lec3.ui.component.Selection
import com.cornellappdev.introandroid.lecturedemos.lec3.util.randomSelect


@Composable
fun MainScreen() {
    var select by remember { mutableIntStateOf(0) }

    var open1 by remember { mutableStateOf(false) }
    var items1 by remember { mutableStateOf(randomSelect()) }

    var open2 by remember { mutableStateOf(false) }
    var items2 by remember { mutableStateOf(randomSelect()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Demo 1: Radio Buttons",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Radial Selection Demo!
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            for (i in 0 until 5) {
                Selection(selected = select == i) {
                    select = i
                }
            }
        }

        HorizontalLine()

        Text(
            text = "Demo 2: Expandable List",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            fontSize = 32.sp
        )

        ExpandableList(
            open = open1,
            items = items1,
            onOpen = {
                open1 = !open1
            },
        )

        HorizontalLine()

        Text(
            text = "Demo 3: Randomizing List",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            fontSize = 32.sp
        )

        // Content Expansion Demo!
        ExpandableList(
            open = open2,
            items = items2,
            onOpen = {
                open2 = !open2
            },
            onRandomize = {
                items2 = randomSelect()
            }
        )

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
private fun HorizontalLine() {
    Spacer(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 20.dp)
            .background(Color.Gray)
            .height(1.dp)
            .fillMaxWidth()
    )
}
