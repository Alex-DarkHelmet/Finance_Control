package com.alex_bystrov.safemoney.domain.features.calculate

import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import com.alex_bystrov.safemoney.domain.features.calculate.repository.CalculateInputRepository
import com.alex_bystrov.safemoney.domain.features.calculate.repository.CalculateBalanceRepository

class Calculate(
    private val repository: CalculateInputRepository
) : CalculateBalanceRepository {

    override fun getCalculatedInput(input: String): String {
        return repository.getCalculatedInput(input)
    }

    override fun getCalculatedBalance(
        currentBalance: Double,
        transaction: UserTransactionModel
    ): Double {
        return calculateBalanceByType(
            currentBalance = currentBalance,
            transaction = transaction
        )
    }

    override fun getDailyTotalSum(
        transactions: List<UserTransactionModel>, isExpense: Boolean
    ): Double {
        return getDailySum(transactions = transactions, isExpense = isExpense)
    }


    private fun calculateBalanceByType(
        currentBalance: Double, transaction: UserTransactionModel
    ): Double {
        return when (transaction.type) {
            TypeTransactionModel.Income -> currentBalance.plus(transaction.amount)
            TypeTransactionModel.Expense -> currentBalance.minus(transaction.amount)
        }
    }

    private fun getDailySum(transactions: List<UserTransactionModel>, isExpense: Boolean): Double {
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