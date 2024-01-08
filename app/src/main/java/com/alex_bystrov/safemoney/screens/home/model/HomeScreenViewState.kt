package com.alex_bystrov.safemoney.screens.home.model

import com.alex_bystrov.safemoney.domain.common.DailyTotalModel

sealed class HomeScreenViewState {
    object NoItems: HomeScreenViewState()
    data class Display(
        val currentMonth: String,
        val expenseByMonth: Double = 0.0,
        val incomeByMonth: Double = 0.0,
        val balanceByMonth: Double = 0.0,
        val totalBalance: Double = 0.0,
        val dailyTransactions: List<DailyTotalModel> = emptyList()
    ): HomeScreenViewState()
}