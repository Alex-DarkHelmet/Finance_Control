package com.alex_bystrov.safemoney.domain.features.calculate

import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.balance.model.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel

class Calculations(
    private val converter: Converter
): CalculationRepository {

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
    override fun calculateDailyTotal(
        date: String,
        transactions: List<UserTransactionModel>
    ): DailyTotalModel {
        val dailyTotalExpense = 0.0
        val dailyTotalIncome = 0.0

        transactions
            .onEach { item ->
                if (item.type == TypeTransactionModel.Expense) {
                    dailyTotalExpense.plus(item.amount)
                } else {
                    dailyTotalIncome.plus(item.amount)
                }
            }

        return DailyTotalModel(
            date = date,
            transactions = transactions,
            dailyExpense = dailyTotalExpense.toString(),
            dailyIncome = dailyTotalIncome.toString()
        )
    }

    override fun getCalculatedInput(userInput: String): String {
        return calculateInput(userInput)
    }

    private fun calculateInput(userInput: String): String {
        var nextInputNum = ""
        var totalInput = ""
        val mathOperator = mutableListOf<Char>()

        for (item in userInput) {
            if (item.isDigit() || item == '.') {
                if (mathOperator.isEmpty()) {
                    totalInput += item.toString()
                } else {
                    nextInputNum += item.toString()
                }
            } else {
                if (mathOperator.isEmpty()) {
                    mathOperator.add(item)
                } else {
                    totalInput = inputCalculationsByType(
                        totalInput = totalInput.toDouble(),
                        operator = mathOperator.first(),
                        nexInputNumber = nextInputNum.toDouble()
                    )

                    mathOperator.clear()
                    mathOperator.add(item)
                    nextInputNum = ""
                }
            }
        }

        if (nextInputNum.isNotEmpty()) {
            totalInput = inputCalculationsByType(
                totalInput = totalInput.toDouble(),
                operator = mathOperator.first(),
                nexInputNumber = nextInputNum.toDouble()
            )
        }

        return converter.formattedSum(totalInput.toDouble())
    }

    private fun inputCalculationsByType(
        totalInput: Double, operator: Char, nexInputNumber: Double
    ): String {
        return if (nexInputNumber != 0.0) {
            when (operator) {
                '+' -> totalInput.plus(nexInputNumber).toString()
                '-' -> totalInput.minus(nexInputNumber).toString()
                '*' -> totalInput.times(nexInputNumber).toString()
                '/' -> totalInput.div(nexInputNumber).toString()
                else ->throw RuntimeException("Unknown operator - $operator")
            }
        } else "0"
    }
}