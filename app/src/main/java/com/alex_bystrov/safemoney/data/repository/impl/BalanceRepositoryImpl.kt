package com.alex_bystrov.safemoney.data.repository.impl

import com.alex_bystrov.safemoney.data.source.local.dao.BalanceDao
import com.alex_bystrov.safemoney.data.source.local.entities.mapToDomainModel
import com.alex_bystrov.safemoney.data.repository.BalanceDataRepository
import com.alex_bystrov.safemoney.data.source.local.entities.BalanceEntity
import com.alex_bystrov.safemoney.domain.features.balance.models.BalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.models.mapToDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform

class BalanceRepositoryImpl(
    private val balanceLocalDataSource: BalanceDao,
) : BalanceDataRepository {


    override suspend fun getBalanceFromMonth(yearMonth: String): Flow<BalanceModel> {
        return balanceLocalDataSource.getBalanceFromMonth(yearMonth = yearMonth)?.transform { item ->
            item.mapToDomainModel()
        } ?: flow {
            emit(
                value = BalanceEntity(
                    yearMonth = yearMonth,
                    expenseSum = 0.0,
                    incomeSum = 0.0,
                ).mapToDomainModel()
            )
        }
    }

    override suspend fun getBalanceByPeriod(
        startDate: String,
        endDate: String
    ): List<BalanceModel> {
        TODO("Not yet implemented")
    }

    override suspend fun updateBalance(balance: BalanceModel) {
        balanceLocalDataSource.updateBalance(balance = balance.mapToDataEntity())
    }

    override suspend fun insertBalance(balance: BalanceModel) {
        balanceLocalDataSource.insertBalance(balance = balance.mapToDataEntity())
    }
}