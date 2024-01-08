package com.alex_bystrov.safemoney.domain.features.transactions

import com.alex_bystrov.safemoney.data.repository.TransactionsDataRepository
import com.alex_bystrov.safemoney.domain.common.CategoryModel
import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.balance.BalanceRepository
import com.alex_bystrov.safemoney.domain.features.calendar.CalendarRepository
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@HiltViewModel
class UserTransactions @Inject constructor(
    private val transactionsRepository: TransactionsDataRepository,
    private val balanceRepository: BalanceRepository,
    private val calendarRepository: CalendarRepository,
) : UserTransactionsRepository {

    override suspend fun getDailyTransactions(date: String): DailyTotalModel {
        TODO()
    }

    override suspend fun getChosenTransaction(id: Long): UserTransactionModel {
        return transactionsRepository.getChosenTransaction(id = id)
    }

    //refactor this method
    override suspend fun getMonthlyTransactions(date: String): Flow<List<DailyTotalModel>> {
        val dateStart = calendarRepository.getFirstAndLastDayInMonth(date, true)
        val dateEnd = calendarRepository.getFirstAndLastDayInMonth(date, false)
        val transactionsFlow = transactionsRepository.getMonthlyTransactions(dateStart = dateStart, dateEnd = dateEnd)

        return flow {
            val dailyTransactions = mutableMapOf<String, MutableList<UserTransactionModel>>()

            transactionsFlow.collect { transactions ->
                transactions.onEach {
                    dailyTransactions.computeIfAbsent(it.date) { mutableListOf() }.add(it)
                }
            }

            val dailyTotal = dailyTransactions.map { (key, value) ->
                balanceRepository.getDailyBalance(key, value)
            }

            emit(dailyTotal)
        }
    }

    override suspend fun getCategoriesByType(type: TypeTransactionModel): List<CategoryModel> {
        return transactionsRepository.getCategoriesByType(type = type.name)
    }

    override suspend fun updateTransaction(id: Long, newValues: UserTransactionModel) {
        return transactionsRepository.updateTransaction(newValues = newValues)
    }

    override suspend fun addTransaction(transaction: UserTransactionModel) {
        transactionsRepository.addTransaction(transaction)
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        val chosenTransaction = transactionsRepository.getChosenTransaction(transactionId)

        transactionsRepository.deleteTransaction(chosenTransaction)
    }

    private suspend fun getCategoryTransactionName(transaction: UserTransactionModel): String {
        return transactionsRepository.getCategory(transaction.category).category
    }

    private suspend fun getTransactionsByDay(date: String): Map<String, List<UserTransactionModel>> {
        val transactionsFlow = transactionsRepository.getDailyTransactions(date = date)

        return transactionsFlow
            .map {transactions ->
                transactions.filter { item -> item.date == date }
            }
            .toList()
            .let {filteredTransactions ->
                mapOf(date to filteredTransactions.flatten())
            }
    }

//    private fun getCalculatedDailyTransactions(date: String): Flow<DailyTotalModel> = flow {
//        transactions.getDailyTransactions(date = date).map { transactions ->
//            val expenseSum = calculateBalance.getDailyTotalSum(transactions = transactions, isExpense = true)
//            val incomeSum = calculateBalance.getDailyTotalSum(transactions = transactions, isExpense = false)
//
//            emit(
//                DailyTotalModel(
//                    date = date,
//                    transactions = transactions,
//                    totalExpenseByDay = expenseSum,
//                    totalIncomeByDay = incomeSum
//                )
//            )
//        }
//    }
}