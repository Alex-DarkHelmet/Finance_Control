package com.alex_bystrov.safemoney.domain.features.calculate.repository

import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel

interface CalculateBalanceRepository {

    fun getCalculatedInput(input: String): String

    fun getCalculatedBalance(currentBalance: Double, transaction: UserTransactionModel): Double

    fun getDailyTotalSum(transactions: List<UserTransactionModel>, isExpense: Boolean): Double
}