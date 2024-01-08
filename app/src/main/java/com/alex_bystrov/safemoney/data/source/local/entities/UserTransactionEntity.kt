package com.alex_bystrov.safemoney.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel

@Entity(
    tableName = "user_transactions",
    indices = [Index("category_id")],
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
        )
    ]
)
data class UserTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "date_transaction")
    val date: String,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "type_transaction")
    val type: String,

    @ColumnInfo(name = "emoji")
    val emoji: Int,

    @ColumnInfo(name = "category_id")
    val categoryId: Long,

    @ColumnInfo(name = "note")
    val note: String
)

fun UserTransactionEntity.mapToUserTransactionModel(): UserTransactionModel =
    UserTransactionModel(
        id = id,
        date = date,
        amount = amount,
        type = TypeTransactionModel.valueOf(type),
        emoji = emoji,
        category = categoryId,
        note = note
    )
