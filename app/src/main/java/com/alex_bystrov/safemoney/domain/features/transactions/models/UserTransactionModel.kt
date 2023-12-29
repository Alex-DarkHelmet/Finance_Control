package com.alex_bystrov.safemoney.domain.features.transactions.models

import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.data.source.local.entities.UserTransactionEntity

data class UserTransactionModel(
    val id: Long,
    val date: String,
    val amount: Double = 0.0,
    val type: TypeTransactionModel = TypeTransactionModel.Expense,
    val emoji: Int = R.drawable.default_icon_24,
    val category: Long,
    val note: String = ""
)

enum class TypeTransactionModel {
    Income, Expense
}

fun UserTransactionModel.mapToTransactionDataEntity(): UserTransactionEntity =
    UserTransactionEntity(
        id = id,
        date = date,
        amount = amount,
        type = type.name,
        emoji = emoji,
        categoryId = category,
        note = note
    )