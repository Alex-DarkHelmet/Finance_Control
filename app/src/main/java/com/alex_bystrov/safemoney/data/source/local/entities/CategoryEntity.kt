package com.alex_bystrov.safemoney.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alex_bystrov.safemoney.domain.common.CategoryModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel

@Entity(
    tableName = "category_entity",
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Long,

    @ColumnInfo(name = "icon")
    val icon: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "category")
    val category: String,
)

fun CategoryEntity.mapToCategoryModel(): CategoryModel =
    CategoryModel(
        id = id,
        icon = icon,
        type = TypeTransactionModel.valueOf(type),
        category = category,
    )