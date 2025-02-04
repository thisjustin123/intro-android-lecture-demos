package com.example.animdemo.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animdemo.ui.component.ExpandableList
import com.example.animdemo.ui.component.Selection
import com.example.animdemo.util.randomSelect


@Composable
fun MainScreen() {
    var select by remember { mutableIntStateOf(0) }

    var open by remember { mutableStateOf(false) }

    var items by remember { mutableStateOf(randomSelect()) }

    var randomSize by remember { mutableFloatStateOf(32f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
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

        // Content Expansion Demo!
        ExpandableList(
            open = open,
            items = items,
            onOpen = {
                open = !open
            },
            onRandomize = {
                items = randomSelect()
            }
        )

        HorizontalLine()

        Column(modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .padding(10.dp)
            // TODO: Add a modifier `animateContentSize` to animate the size of the Column!
            .animateContentSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                randomSize = if (randomSize == 32f) 64f else 32f
            }
        ) {
            Text(
                text = "Tap to change size!",
                modifier = Modifier.height(randomSize.dp)
            )
        }

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
