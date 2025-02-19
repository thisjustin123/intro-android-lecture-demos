package com.cornellappdev.introandroid.lecturedemos.lec5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cornellappdev.introandroid.lecturedemos.lec5.components.TickerRow

@Composable
fun TickerScreen(
    tickerViewModel: TickerViewModel = viewModel()
) {
    // TODO: Why isn't this causing a load?
    val map = tickerViewModel.mapFlow.collectAsState().value

    LazyColumn(modifier = Modifier.systemBarsPadding()) {
        itemsIndexed(map.entries.toTypedArray()) { index, entry ->
            TickerRow(ticker = entry.key, price = entry.value)

            Spacer(
                modifier = Modifier
                    .background(Color.Gray)
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
    }

}
