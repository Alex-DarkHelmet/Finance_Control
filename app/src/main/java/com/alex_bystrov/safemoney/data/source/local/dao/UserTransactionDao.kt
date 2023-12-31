package com.alex_bystrov.safemoney.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alex_bystrov.safemoney.data.source.local.entities.CategoryEntity
import com.alex_bystrov.safemoney.data.source.local.entities.UserTransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTransactionDao {

    @Query("SELECT * FROM user_transactions WHERE date_transaction BETWEEN :dateStart AND :dateEnd")
    fun getMonthlyTransactions(dateStart: String, dateEnd: String): Flow<List<UserTransactionEntity>>

    @Query("SELECT * FROM user_transactions WHERE date_transaction =:date")
    fun getDailyTransactions(date: String): Flow<List<UserTransactionEntity>>

    @Query("SELECT * FROM user_transactions WHERE id = :id")
    fun getTransactionByChosenDay(id: Long): UserTransactionEntity

    @Query("SELECT * FROM category_entity WHERE category_id = :id")
    fun getCategoryName(id: Long): CategoryEntity

    @Delete
    suspend fun deleteTransaction(transaction: UserTransactionEntity)

    @Update
    suspend fun updateTransaction(newValues: UserTransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(newTransaction: UserTransactionEntity)
}