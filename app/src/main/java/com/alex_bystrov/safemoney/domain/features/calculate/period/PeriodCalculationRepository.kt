package com.alex_bystrov.safemoney.domain.features.calculate.period

import com.alex_bystrov.safemoney.domain.features.balance.period.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel

interface PeriodCalculationRepository {

    fun getCalculatedMonthlyBalance(balance: MonthlyBalanceModel, transaction: UserTransactionModel): MonthlyBalanceModel

    fun getDailyTotalSum(transactions: List<UserTransactionModel>, isExpense: Boolean): Double
}