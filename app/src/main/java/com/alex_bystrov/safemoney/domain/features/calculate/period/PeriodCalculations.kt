package com.alex_bystrov.safemoney.domain.features.calculate.period

import com.alex_bystrov.safemoney.domain.features.balance.period.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel

class PeriodCalculations: PeriodCalculationRepository {

    override fun getCalculatedMonthlyBalance(
        balance: MonthlyBalanceModel,
        transaction: UserTransactionModel
    ): MonthlyBalanceModel {
        return when (transaction.type) {
            TypeTransactionModel.Income -> {
                balance.copy(
                    incomeSum = balance.incomeSum.plus(transaction.amount),
                    currentBalance = balance.incomeSum.minus(balance.expenseSum)
                )
            }
            TypeTransactionModel.Expense -> {
                balance.copy(
                    expenseSum = balance.expenseSum.plus(transaction.amount),
                    currentBalance = balance.incomeSum.minus(balance.expenseSum)
                )
            }
        }
    }
    override fun getDailyTotalSum(
        transactions: List<UserTransactionModel>,
        isExpense: Boolean
    ): Double {
        return transactions
            .filter { item ->
                if (isExpense) {
                    item.type == TypeTransactionModel.Expense
                } else {
                    item.type == TypeTransactionModel.Income
                }
            }
            .sumOf { it.amount }
    }
}