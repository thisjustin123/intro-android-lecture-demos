package com.cornellappdev.introandroid.lecturedemos.lec5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cornellappdev.introandroid.lecturedemos.lec5.util.UIEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TickerViewModel : ViewModel() {

    val mapFlow: MutableStateFlow<Map<String, Double>> = MutableStateFlow(
        mapOf(
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
        )
    )

    val scrollEventFlow: MutableStateFlow<UIEvent<Unit>?> = MutableStateFlow(null)

    /**
     * Fetches ticker values one by one from our very real server.
     */
    suspend fun loadTickerValues() {
        for (ticker in mapFlow.value.keys) {
            mapFlow.value = mapFlow.value.toMutableMap().apply {
               TickerRepository.getTickerValue(ticker).let { this[ticker] = it }
            }
        }
    }

    fun onTickerButtonClick(ticker: String, delta: Double) {
        mapFlow.value = mapFlow.value.toMutableMap().apply {
            this[ticker] = (this[ticker] ?: 0.0) + delta
        }
    }

    fun onScrollToTopClicked() {
        scrollEventFlow.value = UIEvent(Unit)
    }

    init {
        viewModelScope.launch {
            delay(500)
            loadTickerValues()
        }
    }
}
