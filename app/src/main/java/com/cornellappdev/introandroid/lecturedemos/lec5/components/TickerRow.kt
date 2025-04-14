package com.cornellappdev.introandroid.lecturedemos.lec5.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale

// TODO: Make it dumber! :-)
@Composable
fun TickerRow(
    ticker: String,
    price: Double,
    modifier: Modifier = Modifier
) {
    val priceState = remember { mutableDoubleStateOf(price) }

    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = ticker,
            fontWeight = FontWeight.Bold
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            // (Optional) TODO: Use TickerText for improved animations!
            Text(text = "\$${String.format(Locale.US, "%.2f", priceState.doubleValue)}")

            Spacer(modifier = Modifier.size(8.dp))

            // Reused component!
            ChangeButton(
                text = "+",
                color = Color.Green,
                onClick = { priceState.value += 1.0 }
            )

            Spacer(modifier = Modifier.size(8.dp))

            // Reused component!
            ChangeButton(
                text = "-",
                color = Color.Red,
                onClick = { priceState.value -= 1.0 }
            )
        }
    }
}

// Improves code reuse!
@Composable
private fun ChangeButton(
    text: String,
    onClick: () -> Unit = {},
    color: Color = Color.Green
) {
    Surface(
        shape = RoundedCornerShape(3.dp),
        color = color,
        border = BorderStroke(2.dp, Color.Black),
        onClick = onClick,
        modifier = Modifier.size(32.dp)
    ) {
        Box(modifier = Modifier.size(32.dp)) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun TickerRowPreview() {
    Box(modifier = Modifier.background(Color.White)) {
        TickerRow(ticker = "AAPL", price = 100.0)
    }
}
