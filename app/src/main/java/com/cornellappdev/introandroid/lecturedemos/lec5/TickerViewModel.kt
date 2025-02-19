package com.cornellappdev.introandroid.lecturedemos.lec5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        )
    )

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

    init {
        viewModelScope.launch {
            delay(500)
            loadTickerValues()
        }
    }
}
