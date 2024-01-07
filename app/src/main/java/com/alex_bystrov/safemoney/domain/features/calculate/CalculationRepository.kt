package com.alex_bystrov.safemoney.domain.features.calculate

import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.balance.model.MonthlyBalanceModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel

interface CalculationRepository {

    fun getCalculatedMonthlyBalance(balance: MonthlyBalanceModel, transaction: UserTransactionModel): MonthlyBalanceModel

    fun calculateDailyTotal(date: String, transactions: List<UserTransactionModel>): DailyTotalModel

    fun getCalculatedInput(userInput: String): String
}