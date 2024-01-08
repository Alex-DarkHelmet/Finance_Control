package com.alex_bystrov.safemoney.domain.features.balance.model

import com.alex_bystrov.safemoney.data.source.local.entities.TotalBalanceEntity

data class TotalBalanceModel(
    val id: Long,
    val totalAmount: Double
)

fun TotalBalanceModel.mapToDataTotalBalance() : TotalBalanceEntity =
    TotalBalanceEntity(
        totalBalanceId = id,
        totalAmount = totalAmount
    )
