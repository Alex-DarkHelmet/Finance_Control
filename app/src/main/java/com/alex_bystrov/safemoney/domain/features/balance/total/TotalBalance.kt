package com.alex_bystrov.safemoney.domain.features.balance.total

import com.alex_bystrov.safemoney.data.repository.TotalBalanceDataRepository
import kotlinx.coroutines.flow.Flow

class TotalBalance(
    private val totalBalanceLocalDataSource: TotalBalanceDataRepository
): TotalBalanceRepository {
    override suspend fun getTotalBalance(): Flow<TotalBalanceModel> {
        return totalBalanceLocalDataSource.getTotalBalance()
    }

    override suspend fun updateTotalBalance(newValue: Double) {
        totalBalanceLocalDataSource.updateTotalBalance(newValue = newValue)
    }
}