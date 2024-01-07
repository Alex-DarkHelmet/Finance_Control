package com.alex_bystrov.safemoney.domain.features.transactions

import com.alex_bystrov.safemoney.domain.common.CategoryModel
import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import kotlinx.coroutines.flow.Flow

interface UserTransactionsRepository {

    suspend fun getDailyTransactions(date: String): DailyTotalModel

    suspend fun getChosenTransaction(id: Long): UserTransactionModel

    suspend fun getMonthlyTransactions(date: String): Flow<List<DailyTotalModel>>

    suspend fun getCategoriesByType(type: TypeTransactionModel): List<CategoryModel>

    suspend fun updateTransaction(id: Long, newValues: UserTransactionModel)

    suspend fun addTransaction(transaction: UserTransactionModel)

    suspend fun deleteTransaction(transactionId: Long)
}