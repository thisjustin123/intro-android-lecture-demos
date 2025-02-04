package com.cornellappdev.introandroid.lecturedemos.lec3.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandableList(
    open: Boolean,
    items: List<String>,
    onOpen: () -> Unit,
    onRandomize: (() -> Unit)? = null,
) {
    Column(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
        .fillMaxWidth()
        .padding(10.dp)
        // TODO: Add a modifier `animateContentSize` to animate the size of the Column!
//        .animateContentSize()
        .clickable {
            onOpen()
        }
    ) {
        Text("Tap to show and hide", fontSize = 20.sp)

        // Conditional Rendering that changes the size of the Column ^
        if (open) {
            Spacer(Modifier.height(8.dp))

            // TODO: Add an AnimatedContent to pop in & out the new items!
//            AnimatedContent(targetState = items, label = "Swap") { items ->
                Column {
                    items.forEach {
                        Text(it, modifier = Modifier.padding(start = 8.dp, bottom = 10.dp))
                    }
                }
//            }

            if (onRandomize != null) {
                Button(onClick = { onRandomize() }) {
                    Text("Randomize!")
                }
            }
        }
    }
}
