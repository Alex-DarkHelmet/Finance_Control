package com.alex_bystrov.safemoney.data.repository.impl

import com.alex_bystrov.safemoney.data.repository.TransactionsDataRepository
import com.alex_bystrov.safemoney.data.source.local.dao.UserTransactionDao
import com.alex_bystrov.safemoney.data.source.local.entities.mapToCategoryModel
import com.alex_bystrov.safemoney.data.source.local.entities.mapToUserTransactionModel
import com.alex_bystrov.safemoney.domain.common.CategoryModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.mapToTransactionDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class UserTransactionsRepositoryImpl(
    private val transactionsLocalDataSource: UserTransactionDao
) : TransactionsDataRepository {
    override suspend fun getMonthlyTransactions(
        dateStart: String,
        dateEnd: String
    ): Flow<List<UserTransactionModel>> {
        return transactionsLocalDataSource.getMonthlyTransactions(dateStart = dateStart, dateEnd = dateEnd)
            .transform { items ->
                items.map { item ->
                    item.mapToUserTransactionModel()
                }
            }
    }

    override suspend fun getDailyTransactions(date: String): Flow<List<UserTransactionModel>> {
        return transactionsLocalDataSource.getDailyTransactions(date = date).transform { items ->
            items.map {
                it.mapToUserTransactionModel()
            }
        }
    }

    override suspend fun getChosenTransaction(id: Long): UserTransactionModel {
        return transactionsLocalDataSource.getTransactionByChosenDay(id = id).mapToUserTransactionModel()
    }

    override suspend fun getCategory(id: Long): CategoryModel {
        return transactionsLocalDataSource.getCategoryName(id).mapToCategoryModel()
    }

    override suspend fun getCategoriesByType(type: String): List<CategoryModel> {
        return transactionsLocalDataSource.getCategoriesByType(type = type).map { category -> category.mapToCategoryModel() }
    }

    override suspend fun deleteTransaction(transaction: UserTransactionModel) {
        return transactionsLocalDataSource.deleteTransaction(transaction = transaction.mapToTransactionDataEntity())
    }

    override suspend fun updateTransaction(newValues: UserTransactionModel) {
        transactionsLocalDataSource.updateTransaction(newValues = newValues.mapToTransactionDataEntity())
    }

    override suspend fun addTransaction(newTransaction: UserTransactionModel) {
        transactionsLocalDataSource.addTransaction(newTransaction = newTransaction.mapToTransactionDataEntity())
    }
}