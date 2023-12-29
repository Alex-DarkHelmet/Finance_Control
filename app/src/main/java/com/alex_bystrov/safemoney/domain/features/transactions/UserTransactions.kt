package com.alex_bystrov.safemoney.domain.features.transactions

import com.alex_bystrov.safemoney.data.repository.TransactionsDataRepository
import com.alex_bystrov.safemoney.domain.features.balance.repository.BalanceRepository
import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.repository.UserTransactionsRepository
import kotlinx.coroutines.flow.Flow

class UserTransactions(
    private val transactionsRepository: TransactionsDataRepository,
    private val balanceRepository: BalanceRepository,
    private val converter: Converter
//    private val calendar: CalendarRepository,
//    private val calculateBalance: CalculateBalanceRepository
) : UserTransactionsRepository {

    override suspend fun getDailyTransactions(date: String): Flow<List<UserTransactionModel>> {
        return transactionsRepository.getDailyTransactions(date = date)
    }

    override suspend fun getChosenTransaction(id: Long): UserTransactionModel {
        return transactionsRepository.getChosenTransaction(id = id)
    }

    override suspend fun getMonthlyTransactions(
        dateStart: String,
        dateEnd: String
    ): Flow<List<UserTransactionModel>> {
        return transactionsRepository.getMonthlyTransactions(dateStart = dateStart, dateEnd = dateEnd)
    }


    override suspend fun updateTransaction(id: Long, newValues: UserTransactionModel) {
        return transactionsRepository.updateTransaction(newValues = newValues)
    }

    override suspend fun addTransaction(transaction: UserTransactionModel) {
        transactionsRepository.addTransaction(transaction)
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        TODO("Not yet implemented")
    }

//    private suspend fun getTransactionsByDay(date: String): Map<String, List<UserTransactionModel>> {
//        val transactionsFlow = transactions.getDailyTransactions(date = date)
//
//        return transactionsFlow
//            .map {transactions ->
//                transactions.filter { item -> item.date == date }
//            }
//            .toList()
//            .let {filteredTransactions ->
//                mapOf(date to filteredTransactions.flatten())
//            }
//    }

//    private fun getCalculatedDailyTransactions(date: String): Flow<DailyTransactionsModel> = flow {
//        transactions.getDailyTransactions(date = date).map { transactions ->
//            val expenseSum = calculateBalance.getDailyTotalSum(transactions = transactions, isExpense = true)
//            val incomeSum = calculateBalance.getDailyTotalSum(transactions = transactions, isExpense = false)
//
//            emit(
//                DailyTransactionsModel(
//                    date = date,
//                    transactions = transactions,
//                    totalExpenseByDay = expenseSum,
//                    totalIncomeByDay = incomeSum
//                )
//            )
//        }
//    }
}