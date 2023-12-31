package com.alex_bystrov.safemoney.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alex_bystrov.safemoney.domain.features.balance.model.MonthlyBalanceModel

@Entity(
    tableName = "balance",
)
data class BalanceEntity(
    @PrimaryKey(autoGenerate = true)
    val balanceId: Long = 0,

    @ColumnInfo(name = "year_month")
    val yearMonth: String,

    @ColumnInfo(name = "income_sum")
    val incomeSum: Double,

    @ColumnInfo(name = "expense_sum")
    val expenseSum: Double,

    @ColumnInfo(name = "current_balance")
    val currentBalance: Double
)

fun BalanceEntity.mapToDomainModel(): MonthlyBalanceModel =
    MonthlyBalanceModel(
        balanceId = balanceId,
        yearMonth = yearMonth,
        incomeSum = incomeSum,
        expenseSum = expenseSum,
        currentBalance = currentBalance
    )
