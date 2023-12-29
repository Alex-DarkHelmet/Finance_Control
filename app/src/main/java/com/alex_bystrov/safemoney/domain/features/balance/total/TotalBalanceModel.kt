package com.alex_bystrov.safemoney.domain.features.balance.total

import com.alex_bystrov.safemoney.data.source.local.entities.TotalBalanceEntity

data class TotalBalanceModel(
    val id: Long,
    val total: Double
)

fun TotalBalanceModel.mapToDataTotalBalance() : TotalBalanceEntity =
    TotalBalanceEntity(
        totalBalanceId = id,
        total = total
    )
