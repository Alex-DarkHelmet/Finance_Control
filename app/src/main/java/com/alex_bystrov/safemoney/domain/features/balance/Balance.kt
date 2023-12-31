package com.alex_bystrov.safemoney.domain.features.balance

import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.data.repository.BalanceDataRepository
import com.alex_bystrov.safemoney.domain.features.balance.model.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.balance.model.TotalBalanceModel
import com.alex_bystrov.safemoney.domain.features.calculate.CalculationRepository
import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Balance(
    private val balanceLocalDataSource: BalanceDataRepository,
    private val calculateRepository: CalculationRepository,
    private val converter: Converter
) : BalanceRepository {
    override suspend fun getMonthlyBalance(date: String): Flow<MonthlyBalanceModel> {
        val yearMonth = converter.convertDateToYearAndMonth(date)
        return balanceLocalDataSource.getBalanceFromMonth(yearMonth)
    }

    override suspend fun getDailyBalance(transactions: List<UserTransactionModel>): DailyTotalModel {
        return calculateRepository.getDailyTotal(transactions)
    }


    override suspend fun updateMonthlyBalance(transaction: UserTransactionModel) {
        val yearMonth = converter.convertDateToYearAndMonth(transaction.date)

        balanceLocalDataSource.getBalanceFromMonth(yearMonth).map { balance ->
            val calculatedBalance = calculateRepository.getCalculatedMonthlyBalance(balance = balance, transaction = transaction)

            balanceLocalDataSource.updateBalance(calculatedBalance)
        }
    }

    override suspend fun insertMonthlyBalance(balance: MonthlyBalanceModel) {
        balanceLocalDataSource.insertBalance(balance = balance)
    }

    override suspend fun getTotalBalance(): Flow<TotalBalanceModel> {
        return balanceLocalDataSource.getTotalBalance()
    }

    override suspend fun updateTotalBalance(newValue: Double) {
        balanceLocalDataSource.updateTotalBalance(newValue = newValue)
    }
}