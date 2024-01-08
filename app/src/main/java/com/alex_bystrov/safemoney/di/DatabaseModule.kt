package com.alex_bystrov.safemoney.di

import android.content.Context
import androidx.room.Room
import com.alex_bystrov.safemoney.data.repository.impl.BalanceRepositoryImpl
import com.alex_bystrov.safemoney.data.repository.impl.UserTransactionsRepositoryImpl
import com.alex_bystrov.safemoney.data.source.local.SafeMoneyDataBase
import com.alex_bystrov.safemoney.data.source.local.dao.BalanceDao
import com.alex_bystrov.safemoney.data.source.local.dao.UserTransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): SafeMoneyDataBase {
        return Room.databaseBuilder(
            appContext,
            SafeMoneyDataBase::class.java,
            "safe_money_database"
        ).build()
    }

    @Provides
    fun provideTransactionDao(dataBase: SafeMoneyDataBase): UserTransactionDao {
        return dataBase.transactionDao()
    }

    @Provides
    fun transactionsRepository(transactionsDao: UserTransactionDao): UserTransactionsRepositoryImpl {
        return UserTransactionsRepositoryImpl(transactionsDao)
    }

    @Provides
    fun provideBalanceDao(dataBase: SafeMoneyDataBase): BalanceDao {
        return dataBase.balanceDao()
    }

    @Provides
    fun balanceRepository(balanceDao: BalanceDao): BalanceRepositoryImpl {
        return BalanceRepositoryImpl(balanceDao)
    }
}