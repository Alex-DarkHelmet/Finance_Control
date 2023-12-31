package com.alex_bystrov.safemoney.domain.common

import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
data class CategoryModel(
    val id: Long,
    val icon: Int = R.drawable.default_icon_24,
    val type: TypeTransactionModel,
    val category: String,
)

{
    companion object {
        val baseExpenseCategoryList = listOf(
            CategoryModel(
                id = 0,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Expense,
                category = "Food",
            ),
            CategoryModel(
                id = 1,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Expense,
                category = "Transport",
            ),
            CategoryModel(
                id = 2,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Expense,
                category = "Health",
            ),
        )

        val baseIncomeCategoryList = listOf(
            CategoryModel(
                id = 3,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Income,
                category = "Income",
            ),
        )
    }
}

