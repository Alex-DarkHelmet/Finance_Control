package com.alex_bystrov.safemoney.domain.features.balance.period

import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.data.repository.BalanceDataRepository
import com.alex_bystrov.safemoney.domain.features.calculate.period.PeriodCalculationRepository
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PeriodBalance(
    private val balanceLocalDataSource: BalanceDataRepository,
    private val calculateRepository: PeriodCalculationRepository,
    private val converter: Converter
) : PeriodBalanceRepository {
    override suspend fun getMonthlyBalance(date: String): Flow<MonthlyBalanceModel> {
        val yearMonth = converter.convertDateToYearAndMonth(date)
        return balanceLocalDataSource.getBalanceFromMonth(yearMonth)
    }

    override suspend fun updateMonthlyBalance(transaction: UserTransactionModel) {
        val yearMonth = converter.convertDateToYearAndMonth(transaction.date)

        balanceLocalDataSource.getBalanceFromMonth(yearMonth).map { balance ->
            val calculatedBalance = calculateRepository.getCalculatedMonthlyBalance(balance = balance, transaction = transaction)

            balanceLocalDataSource.updateBalance(calculatedBalance)
        }
    }

    override suspend fun getBalanceByPeriod(
        period: String, transactions: List<UserTransactionModel>
    ) {
        TODO("Not yet implemented")
        // make it in calendar folder and in transactions i have getTransactionsByPeriod method
    }

    override suspend fun insertMonthlyBalance(balance: MonthlyBalanceModel) {
        balanceLocalDataSource.insertBalance(balance = balance)
    }
}