package com.alex_bystrov.safemoney.domain.features.balance.total

import kotlinx.coroutines.flow.Flow

interface TotalBalanceRepository {

    suspend fun getTotalBalance(): Flow<TotalBalanceModel>

    suspend fun updateTotalBalance(newValue: Double)
}