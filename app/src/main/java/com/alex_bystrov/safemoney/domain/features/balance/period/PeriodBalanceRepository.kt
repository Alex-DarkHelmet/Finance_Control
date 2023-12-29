package com.alex_bystrov.safemoney.domain.features.balance.period

import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow

interface PeriodBalanceRepository {
    suspend fun getMonthlyBalance(date: String): Flow<MonthlyBalanceModel>?

    suspend fun updateMonthlyBalance(transaction: UserTransactionModel)

    suspend fun getBalanceByPeriod(period: String, transactions: List<UserTransactionModel>)

    suspend fun insertMonthlyBalance(balance: MonthlyBalanceModel)
}