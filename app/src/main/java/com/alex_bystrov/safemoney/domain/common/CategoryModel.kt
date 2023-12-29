package com.alex_bystrov.safemoney.domain.common

import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel
data class SubCategory(
    val id: Long,
    val name: String
)
data class CategoryModel(
    val id: Long,
    val icon: Int = R.drawable.default_icon_24,
    val type: TypeTransactionModel,
    val category: String,
    val subCategory: List<String> = emptyList()
)

{
    companion object {
        val baseExpenseCategoryList = listOf(
            CategoryModel(
                id = 0,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Expense,
                category = "Food",
                subCategory = listOf(
                    "Home", "Restaurant", "Cafe", "Market"
                )
            ),
            CategoryModel(
                id = 1,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Expense,
                category = "Transport",
                subCategory = listOf(
                    "Bus", "Subway", "Taxi", "Airplane"
                )
            ),
            CategoryModel(
                id = 2,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Expense,
                category = "Health",
                subCategory = listOf(
                    "Insurance", "Drugs", "Hospital"
                )
            ),
        )

        val baseIncomeCategoryList = listOf(
            CategoryModel(
                id = 3,
                icon = R.drawable.default_icon_24,
                type = TypeTransactionModel.Income,
                category = "Income",
                subCategory = listOf(
                    "Salary", "Bonus", "Investment"
                )
            ),
        )
    }
}

