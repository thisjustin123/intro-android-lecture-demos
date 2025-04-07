package com.cornellappdev.introandroid.lecturedemos.lec4

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class BankRepository @Inject constructor() {
    // Fake networking stuff.
    private var currentTransactions: List<BankHistoryViewModel.Expense> =
        listOf()

    private var stores =
        listOf("Best Buy", "Target", "Walmart", "Amazon", "Best Buy", "Target", "Walmart", "Amazon")

    suspend fun spawnNewTransaction() {
        val randomStore = stores.random()
        val randomCharge = Random.nextFloat() * 1000f - 500f
        val currentTime = LocalTime.now()
        val formattedTime = currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
        currentTransactions = listOf(
            BankHistoryViewModel.Expense(
                randomStore,
                formattedTime,
                randomCharge
            )
        ) + currentTransactions
        delay(2000)
        spawnNewTransaction()
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            spawnNewTransaction()
        }
    }

    suspend fun getTransactions(): List<BankHistoryViewModel.Expense> {
        // Simulate a wait time...
        delay(2000)
        return currentTransactions
    }
}
