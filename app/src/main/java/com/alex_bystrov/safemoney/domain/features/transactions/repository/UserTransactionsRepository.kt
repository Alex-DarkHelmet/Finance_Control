package com.alex_bystrov.safemoney.domain.features.transactions.repository

import androidx.room.Transaction
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow

interface UserTransactionsRepository {

    suspend fun getDailyTransactions(date: String): Flow<List<UserTransactionModel>>

    suspend fun getChosenTransaction(id: Long): UserTransactionModel

    suspend fun getMonthlyTransactions(dateStart: String, dateEnd: String): Flow<List<UserTransactionModel>>

    suspend fun updateTransaction(id: Long, newValues: UserTransactionModel)

    suspend fun addTransaction(transaction: UserTransactionModel)

    suspend fun deleteTransaction(transactionId: Long)
}