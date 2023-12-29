package com.alex_bystrov.safemoney.domain.features.balance.models

import com.alex_bystrov.safemoney.data.source.local.entities.BalanceEntity

data class BalanceModel(
    val balanceId: Long,
    val yearMonth: String,
    val incomeSum: Double = 0.0,
    val expenseSum: Double = 0.0,
)

fun BalanceModel.mapToDataEntity(): BalanceEntity =
    BalanceEntity(
        balanceId = balanceId,
        yearMonth = yearMonth,
        incomeSum = incomeSum,
        expenseSum = expenseSum,
    )
