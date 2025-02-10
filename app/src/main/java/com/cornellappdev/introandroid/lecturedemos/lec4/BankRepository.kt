package com.cornellappdev.introandroid.lecturedemos.lec4

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class BankRepository @Inject constructor() {
    // Fake networking stuff.
    private var currentTransactions =
        listOf(BankHistoryViewModel.Expense("Best Buy", "2022-01-01", -800f))

    private var stores =
        listOf("Best Buy", "Target", "Walmart", "Amazon", "Best Buy", "Target", "Walmart", "Amazon")

    suspend fun spawnNewTransaction() {
        val randomStore = stores.random()
        val randomCharge = Random.nextFloat() * 1000f - 500f
        currentTransactions = listOf(
            BankHistoryViewModel.Expense(
                randomStore,
                "2025-02-10",
                randomCharge
            )
        ) + currentTransactions
        delay(500)
        spawnNewTransaction()
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            spawnNewTransaction()
        }
    }

    suspend fun getTransactions(): List<BankHistoryViewModel.Expense> {
        // Simulate a wait time...
        delay(1000)
        return currentTransactions
    }
}
