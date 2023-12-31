package com.alex_bystrov.safemoney.domain.features.balance

import com.alex_bystrov.safemoney.domain.features.balance.model.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.model.TotalBalanceModel
import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow

interface BalanceRepository {
    suspend fun getMonthlyBalance(date: String): Flow<MonthlyBalanceModel>?

    suspend fun getDailyBalance(transactions: List<UserTransactionModel>): DailyTotalModel
    suspend fun updateMonthlyBalance(transaction: UserTransactionModel)

    suspend fun insertMonthlyBalance(balance: MonthlyBalanceModel)

    suspend fun getTotalBalance(): Flow<TotalBalanceModel>

    suspend fun updateTotalBalance(newValue: Double)
}