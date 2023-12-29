package com.alex_bystrov.safemoney.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alex_bystrov.safemoney.data.source.local.dao.BalanceDao
import com.alex_bystrov.safemoney.data.source.local.dao.UserTransactionDao
import com.alex_bystrov.safemoney.data.source.local.entities.BalanceEntity
import com.alex_bystrov.safemoney.data.source.local.entities.CategoryEntity
import com.alex_bystrov.safemoney.data.source.local.entities.TotalBalanceEntity
import com.alex_bystrov.safemoney.data.source.local.entities.UserTransactionEntity

@Database(
    entities = [
        UserTransactionEntity::class,
        BalanceEntity::class,
        TotalBalanceEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class SafeMoneyDataBase : RoomDatabase() {
    abstract fun transactionDao(): UserTransactionDao
    abstract fun balanceDao(): BalanceDao
}
