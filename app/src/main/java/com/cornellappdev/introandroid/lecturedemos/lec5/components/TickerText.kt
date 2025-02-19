package com.cornellappdev.introandroid.lecturedemos.lec5.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun TickerText(
    price: Double,
    modifier: Modifier = Modifier
) {
    val previousPrice = remember { mutableStateOf(0.0) }
    var increase: Boolean by remember { mutableStateOf(true) }

    LaunchedEffect(price) {
        increase = price >= previousPrice.value
        previousPrice.value = price
    }

    AnimatedContent(
        targetState = price,
        modifier = modifier
    ) { targetPrice ->
        Text(
            text = "\$${String.format(Locale.US, "%.2f", targetPrice)}",
            color = if (increase) Color.Green else Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
