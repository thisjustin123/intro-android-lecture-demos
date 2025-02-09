package com.cornellappdev.introandroid.lecturedemos.lec4

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankHistoryViewModel @Inject constructor(
    private val bankRepository: BankRepository
) : ViewModel() {

    data class BankHistoryUIState(
        val username: String,
        // Nearest to the beginning is the most recent.
        val transactions: List<Expense>,
        val loading: Boolean = true,
    ) {
        // Red if most recent transaction is negative, green if positive
        val balanceColor
            get() = transactions.getOrNull(0)?.amount.let { mostRecentTransaction ->
                if (mostRecentTransaction != null && mostRecentTransaction < 0) {
                    Color.Red
                } else {
                    Color.Green
                }
            }

        val balance
            get() = transactions.sumOf { it.amount.toDouble() }.toFloat() + 500f

        val balanceText
            get() = "$${String.format("%.2f", balance)}"
    }

    data class Expense(
        val title: String,
        val date: String,
        val amount: Float
    )

    private val _uiStateFlow = MutableStateFlow(
        BankHistoryUIState(
            username = "Markiplier",
            transactions = listOf()
        )
    )
    val uiStateFlow = _uiStateFlow.asStateFlow()

    fun onRefresh() {
        viewModelScope.launch {
            _uiStateFlow.value = _uiStateFlow.value.copy(
                loading = true
            )
            // This networking call will take a while...
            val newTransactions = bankRepository.getTransactions()

            // Networking completed!
            _uiStateFlow.value = _uiStateFlow.value.copy(
                transactions = newTransactions,
                loading = false
            )
        }
    }
}
