package com.cornellappdev.introandroid.lecturedemos.lec5

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
    val uiState = tickerViewModel.uiStateFlow.collectAsState().value
    val stocks = uiState.stocks

    val scrollState = rememberLazyListState()

    // TODO: Write a LaunchedEffect to consume the UIEvent, then scroll to the top!

    Box {
        LazyColumn(
            modifier = Modifier.systemBarsPadding(),
            state = scrollState
        ) {
            itemsIndexed(stocks.entries.toTypedArray()) { index, entry ->
                TickerRow(ticker = entry.key, price = entry.value)

                Spacer(
                    modifier = Modifier
                        .background(Color.Gray)
                        .height(1.dp)
                        .fillMaxWidth()
                )
            }
        }

        AnimatedVisibility(
            visible = remember { derivedStateOf { scrollState.firstVisibleItemIndex } }.value > 0,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Button(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                onClick = {
                    tickerViewModel.onScrollToTopClicked()
                }
            ) {
                Text(
                    text = "Back to Top",
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}
