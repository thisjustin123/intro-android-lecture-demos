package com.cornellappdev.introandroid.lecturedemos.lec5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.introandroid.lecturedemos.lec5.util.UIEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TickerViewModel : ViewModel() {

    data class UiState(
        val stocks: Map<String, Double>,
        val scrollEvent: UIEvent<Unit>?
    )

    val uiStateFlow: MutableStateFlow<UiState> = MutableStateFlow(
        UiState(
            stocks = mapOf(
                "AAPL" to 0.0,
                "GOOG" to 0.0,
                "MSFT" to 0.0,
                "AMZN" to 0.0,
                "TSLA" to 0.0,
                "META" to 0.0,
                "NFLX" to 0.0,
                "NVDA" to 0.0,
                "TSM" to 0.0,
                "NIO" to 0.0,
                "BABA" to 0.0,
                "BIDU" to 0.0,
                "BMRN" to 0.0,
                "BYND" to 0.0,
                "GME" to 0.0,
                "PLTR" to 0.0,
                "SNAP" to 0.0,
                "SQ" to 0.0,
                "SPOT" to 0.0,
                "TSLA" to 0.0,
                "TWTR" to 0.0,
                "ZM" to 0.0,
                "ZNGA" to 0.0
            ),
            scrollEvent = null
        )
    )

    /**
     * Fetches ticker values one by one from our very real server.
     */
    suspend fun loadTickerValues() {
        val stocks = uiStateFlow.value.stocks
        for (ticker in stocks.keys) {
            uiStateFlow.value = uiStateFlow.value.copy(stocks = stocks.toMutableMap().apply {
                TickerRepository.getTickerValue(ticker).let { this[ticker] = it }
            })
        }
    }

    fun onTickerButtonClick(ticker: String, delta: Double) {
        val stocks = uiStateFlow.value.stocks
        uiStateFlow.value = uiStateFlow.value.copy(stocks = stocks.toMutableMap().apply {
            this[ticker] = (this[ticker] ?: 0.0) + delta
        })
    }

    fun onScrollToTopClicked() {
        uiStateFlow.value = uiStateFlow.value.copy(
            scrollEvent = UIEvent(Unit)
        )
    }

    init {
        viewModelScope.launch {
            delay(500)
            loadTickerValues()
        }
    }
}
