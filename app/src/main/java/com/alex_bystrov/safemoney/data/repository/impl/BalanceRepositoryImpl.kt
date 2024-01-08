package com.alex_bystrov.safemoney.data.repository.impl

import com.alex_bystrov.safemoney.data.repository.BalanceDataRepository
import com.alex_bystrov.safemoney.data.source.local.dao.BalanceDao
import com.alex_bystrov.safemoney.data.source.local.entities.BalanceEntity
import com.alex_bystrov.safemoney.data.source.local.entities.mapToDomainModel
import com.alex_bystrov.safemoney.data.source.local.entities.mapToDomainTotalBalance
import com.alex_bystrov.safemoney.domain.features.balance.model.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.model.TotalBalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.model.mapToDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform

class BalanceRepositoryImpl(
    private val balanceLocalDataSource: BalanceDao,
) : BalanceDataRepository {

    override suspend fun getBalanceFromMonth(yearMonth: String): Flow<MonthlyBalanceModel> {
        return balanceLocalDataSource.getBalanceFromMonth(yearMonth = yearMonth)
            ?.transform { item ->
                item.mapToDomainModel()
            }
            ?: flowOf(
                BalanceEntity(
                    yearMonth = yearMonth,
                    incomeSum = 0.0,
                    expenseSum = 0.0,
                    currentBalance = 0.0
                ).mapToDomainModel()
            )
    }

    override suspend fun getBalanceByPeriod(
        startDate: String,
        endDate: String
    ): List<MonthlyBalanceModel> {
        TODO("Not yet implemented")
    }

    override suspend fun updateBalance(balance: MonthlyBalanceModel) {
        balanceLocalDataSource.updateBalance(balance = balance.mapToDataEntity())
    }

    override suspend fun insertBalance(balance: MonthlyBalanceModel) {
        balanceLocalDataSource.insertBalance(balance = balance.mapToDataEntity())
    }

    override suspend fun getTotalBalance(): Flow<TotalBalanceModel> {
        return balanceLocalDataSource.getTotalBalance().transform {balance ->
            balance.mapToDomainTotalBalance()
        }
    }

    override suspend fun updateTotalBalance(newValue: Double) {
        return balanceLocalDataSource.updateTotalBalance(newValue = newValue)
    }
}