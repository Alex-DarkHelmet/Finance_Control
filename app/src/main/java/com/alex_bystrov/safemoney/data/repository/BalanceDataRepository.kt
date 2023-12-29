package com.alex_bystrov.safemoney.data.repository

import com.alex_bystrov.safemoney.domain.features.balance.period.MonthlyBalanceModel
import kotlinx.coroutines.flow.Flow

interface BalanceDataRepository {
    suspend fun getBalanceFromMonth(yearMonth: String): Flow<MonthlyBalanceModel>

    suspend fun getBalanceByPeriod(startDate: String, endDate: String): List<MonthlyBalanceModel>

    suspend fun updateBalance(balance: MonthlyBalanceModel)

    suspend fun insertBalance(balance: MonthlyBalanceModel)
}