package com.cornellappdev.introandroid.lecturedemos.lec4

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cornellappdev.introandroid.lecturedemos.lec4.component.BankExpenseRow

@Composable
fun BankHistoryScreen(
    bankHistoryViewModel: BankHistoryViewModel = hiltViewModel()
) {
    val uiState by bankHistoryViewModel.uiStateFlow.collectAsState()

    ScreenContent(
        uiState,
        onRefresh = bankHistoryViewModel::onRefresh
    )
}

// Allows for @Preview, if you want. ViewModel arguments cannot be previewed.
@Composable
private fun ScreenContent(
    uiState: BankHistoryViewModel.BankHistoryUIState,
    onRefresh: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Text(
            text = "Bank History",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 64.dp)
        )
        Text(
            text = "Welcome Back, ${uiState.username}!",
            fontSize = 24.sp
        )

        AnimatedContent(
            targetState = uiState,
            modifier = Modifier.weight(1f),
            label = "new transaction"
        ) { uiState ->
            when (uiState.loading) {
                true -> {
                    LoadingContent()
                }

                false -> {
                    LoadedContent(uiState)
                }
            }
        }


        Button(
            onClick = {
                onRefresh()
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "Refresh"
            )
        }
    }
}

@Composable
private fun LoadedContent(uiState: BankHistoryViewModel.BankHistoryUIState) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(
            text = "Balance:",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = uiState.balanceText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = uiState.balanceColor
        )

        Text(
            text = "Recent Transactions:",
            modifier = Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.transactions) { (title, date, amount) ->
                BankExpenseRow(
                    title = title,
                    date = date,
                    amount = amount.toDouble()
                )

                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text = "Loading...",
            fontSize = 24.sp
        )

        Spacer(Modifier.weight(.2f))

        CircularProgressIndicator()
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
fun BankHistoryScreenPreview() {

    ScreenContent(
        uiState = BankHistoryViewModel.BankHistoryUIState(
            username = "John Doe",
            transactions = listOf(
                BankHistoryViewModel.Expense(
                    "McDonalds",
                    "2022-01-01",
                    12.99f
                )
            ),
            loading = false
        ),
        onRefresh = {}
    )
}
