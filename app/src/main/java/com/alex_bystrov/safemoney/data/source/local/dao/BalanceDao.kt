package com.alex_bystrov.safemoney.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alex_bystrov.safemoney.data.source.local.entities.BalanceEntity
import com.alex_bystrov.safemoney.data.source.local.entities.TotalBalanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {
    @Query("SELECT * FROM balance WHERE year_month = :yearMonth")
    fun getBalanceFromMonth(yearMonth: String): Flow<BalanceEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBalance(balance: BalanceEntity)

    @Update
    fun updateBalance(balance: BalanceEntity)

    @Query("SELECT * FROM total_balance_entity")
    fun getTotalBalance(): Flow<TotalBalanceEntity>

    @Query("UPDATE total_balance_entity SET total = :newValue")
    fun updateTotalBalance(newValue: Double)
}