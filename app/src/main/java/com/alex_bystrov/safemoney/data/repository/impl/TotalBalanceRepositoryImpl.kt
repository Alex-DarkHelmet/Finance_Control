package com.alex_bystrov.safemoney.data.repository.impl

import com.alex_bystrov.safemoney.data.repository.TotalBalanceDataRepository
import com.alex_bystrov.safemoney.data.source.local.dao.TotalBalanceDao
import com.alex_bystrov.safemoney.data.source.local.entities.mapToDomainTotalBalance
import com.alex_bystrov.safemoney.domain.features.balance.total.TotalBalanceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class TotalBalanceRepositoryImpl(
    private val totalBalanceLocalDataSource: TotalBalanceDao
) : TotalBalanceDataRepository {

    override suspend fun getTotalBalance(): Flow<TotalBalanceModel> {
        return totalBalanceLocalDataSource.getTotalBalance().transform {balance ->
            balance.mapToDomainTotalBalance()
        }
    }

    override suspend fun updateTotalBalance(newValue: Double) {
        return totalBalanceLocalDataSource.updateTotalBalance(newValue = newValue)
    }
}