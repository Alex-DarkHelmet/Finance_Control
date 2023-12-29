package com.alex_bystrov.safemoney.data.repository

import com.alex_bystrov.safemoney.domain.features.balance.models.TotalBalanceModel
import kotlinx.coroutines.flow.Flow

interface TotalBalanceDataRepository {

    suspend fun getTotalBalance(): Flow<TotalBalanceModel>

    suspend fun updateTotalBalance(newValue: Double)
}