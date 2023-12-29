package com.alex_bystrov.safemoney.data.repository

import com.alex_bystrov.safemoney.domain.features.balance.models.BalanceModel
import kotlinx.coroutines.flow.Flow

interface BalanceDataRepository {

    suspend fun getBalanceFromMonth(yearMonth: String): Flow<BalanceModel>?

    suspend fun getBalanceByPeriod(startDate: String, endDate: String): List<BalanceModel>

    suspend fun updateBalance(balance: BalanceModel)

    suspend fun insertBalance(balance: BalanceModel)
}