package com.alex_bystrov.safemoney.domain.features.balance

import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.data.repository.BalanceDataRepository
import com.alex_bystrov.safemoney.data.repository.TotalBalanceDataRepository
import com.alex_bystrov.safemoney.domain.features.balance.models.BalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.models.TotalBalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.repository.BalanceRepository
import com.alex_bystrov.safemoney.domain.features.calculate.repository.CalculateBalanceRepository
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class Balance(
    private val repository: BalanceDataRepository,
    private val totalBalanceRepository: TotalBalanceDataRepository,
    private val calculateRepository: CalculateBalanceRepository,
    private val converter: Converter
) : BalanceRepository {

    override suspend fun getTotalBalance(): Flow<TotalBalanceModel> {
        return totalBalanceRepository.getTotalBalance()
    }

    override suspend fun updateTotalBalance(transaction: UserTransactionModel) {
        totalBalanceRepository.getTotalBalance()
            .map {  totalBalance ->

            val calculatedBalance = calculateRepository.getCalculatedBalance(
                currentBalance = totalBalance.total,
                transaction = transaction
            )
            totalBalanceRepository.updateTotalBalance(calculatedBalance)
        }
    }

    override suspend fun getMonthlyBalance(yearMonth: String): Flow<BalanceModel>? {
        return repository.getBalanceFromMonth(yearMonth)
    }

    override suspend fun getBalanceByPeriod(
        period: String,
        transactions: List<UserTransactionModel>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMonthlyBalance(transaction: UserTransactionModel) {
        val yearMonth = converter.convertDateToYearAndMonth(transaction.date)


    }

    override suspend fun insertMonthlyBalance(balance: BalanceModel) {
        repository.insertBalance(balance = balance)
    }

}