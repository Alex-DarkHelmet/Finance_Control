package com.alex_bystrov.safemoney.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alex_bystrov.safemoney.domain.features.balance.model.TotalBalanceModel

@Entity(tableName = "total_balance_entity")
data class TotalBalanceEntity(
    @PrimaryKey(autoGenerate = true)
    val totalBalanceId: Long = 0,
    val totalAmount: Double = 0.0
)

fun TotalBalanceEntity.mapToDomainTotalBalance() : TotalBalanceModel =
    TotalBalanceModel(
        id = totalBalanceId,
        totalAmount = totalAmount
    )

