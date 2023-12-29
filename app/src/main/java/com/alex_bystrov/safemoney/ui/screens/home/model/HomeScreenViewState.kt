package com.alex_bystrov.safemoney.ui.screens.home.model

import com.alex_bystrov.safemoney.domain.features.transactions.models.DailyTransactionsModel

sealed class HomeScreenViewState {
    object Error: HomeScreenViewState()
    object NoItems: HomeScreenViewState()
    data class Display(
        val currentMonth: String = "Jan",
        val expenseByMonth: Double = 0.0,
        val incomeByMonth: Double = 0.0,
        val remainsByMonth: Double = 0.0,
        val totalBalance: Double = 0.0,
        val dailyTransactions: List<DailyTransactionsModel> = emptyList()
    ): HomeScreenViewState()
}