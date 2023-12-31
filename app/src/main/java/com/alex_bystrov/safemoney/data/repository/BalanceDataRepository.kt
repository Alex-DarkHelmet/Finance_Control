package com.alex_bystrov.safemoney.data.repository

import com.alex_bystrov.safemoney.domain.features.balance.model.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.model.TotalBalanceModel
import kotlinx.coroutines.flow.Flow

interface BalanceDataRepository {
    suspend fun getBalanceFromMonth(yearMonth: String): Flow<MonthlyBalanceModel>

    suspend fun getBalanceByPeriod(startDate: String, endDate: String): List<MonthlyBalanceModel>

    suspend fun updateBalance(balance: MonthlyBalanceModel)

    suspend fun insertBalance(balance: MonthlyBalanceModel)

    suspend fun getTotalBalance(): Flow<TotalBalanceModel>

    suspend fun updateTotalBalance(newValue: Double)
}