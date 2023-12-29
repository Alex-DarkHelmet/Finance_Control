package com.alex_bystrov.safemoney.data.source.local.dao

import androidx.room.Query
import androidx.room.Update
import com.alex_bystrov.safemoney.data.source.local.entities.TotalBalanceEntity
import kotlinx.coroutines.flow.Flow

interface TotalBalanceDao {

    @Query("SELECT * FROM total_balance_entity")
    suspend fun getTotalBalance(): Flow<TotalBalanceEntity>

    @Query("UPDATE total_balance_entity SET total = :newValue")
    suspend fun updateTotalBalance(newValue: Double)
}