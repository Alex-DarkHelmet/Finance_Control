package com.alex_bystrov.safemoney.domain.features.balance.repository

import com.alex_bystrov.safemoney.domain.features.balance.models.BalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.models.TotalBalanceModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow

interface BalanceRepository {

    suspend fun getTotalBalance(): Flow<TotalBalanceModel>

    suspend fun updateTotalBalance(transaction: UserTransactionModel)

    suspend fun getMonthlyBalance(yearMonth: String): Flow<BalanceModel>?

    suspend fun updateMonthlyBalance(transaction: UserTransactionModel)

    suspend fun getBalanceByPeriod(period: String, transactions: List<UserTransactionModel>)

    suspend fun insertMonthlyBalance(balance: BalanceModel)
}