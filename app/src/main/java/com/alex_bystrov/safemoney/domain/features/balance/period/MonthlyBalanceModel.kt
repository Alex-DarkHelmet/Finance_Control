package com.alex_bystrov.safemoney.domain.features.balance.period

import com.alex_bystrov.safemoney.data.source.local.entities.BalanceEntity

data class MonthlyBalanceModel(
    val balanceId: Long,
    val yearMonth: String,
    val incomeSum: Double = 0.0,
    val expenseSum: Double = 0.0,
    val currentBalance: Double = 0.0
)

fun MonthlyBalanceModel.mapToDataEntity(): BalanceEntity =
    BalanceEntity(
        balanceId = balanceId,
        yearMonth = yearMonth,
        incomeSum = incomeSum,
        expenseSum = expenseSum,
        currentBalance = currentBalance
    )
