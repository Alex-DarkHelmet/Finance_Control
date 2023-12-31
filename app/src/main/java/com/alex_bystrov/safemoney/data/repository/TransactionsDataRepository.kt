package com.alex_bystrov.safemoney.data.repository

import com.alex_bystrov.safemoney.domain.common.CategoryModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionsDataRepository {
    suspend fun getMonthlyTransactions(dateStart: String, dateEnd: String): Flow<List<UserTransactionModel>>

    suspend fun getDailyTransactions(date: String): Flow<List<UserTransactionModel>>

    suspend fun getChosenTransaction(id: Long): UserTransactionModel

    suspend fun getCategory(id: Long): CategoryModel

    suspend fun deleteTransaction(transaction: UserTransactionModel)

    suspend fun updateTransaction(newValues: UserTransactionModel)

    suspend fun addTransaction(newTransaction: UserTransactionModel)
}