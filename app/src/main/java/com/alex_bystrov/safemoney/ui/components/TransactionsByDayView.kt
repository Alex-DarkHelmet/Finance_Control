package com.alex_bystrov.safemoney.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.UserTransactionModel
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyAppTheme
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyTheme

@Composable
fun TransactionsByDayView(
    modifier: Modifier = Modifier,
    date: String,
    model: DailyTotalModel
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(SafeMoneyAppTheme.colors.containerColor),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = modifier
                    .padding(start = 5.dp)
                    .weight(1f),
                fontSize = 15.sp,
                fontWeight = FontWeight(500),
                color = Color.White, // change to own typography
                text = date
            )
            // title of transactions
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center
            ) {
                if (model.dailyIncome.isNotEmpty()) {
                    Text(
                        modifier = modifier
                            .padding(end = 10.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Green, // change to own typography
                        text = stringResource(id = R.string.income_title)
                    )
                }

                Text(
                    modifier = modifier
                        .padding(end = 10.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color.LightGray, // change to own typography
                    text = stringResource(id = R.string.expense_title)
                )

            }
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (model.dailyIncome.isNotEmpty()) {
                    Text(
                        modifier = modifier
                            .padding(end = 5.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Green, // change to own typography
                        text = model.dailyIncome
                    )
                }

                Text(
                    modifier = modifier
                        .padding(end = 5.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White, // change to own typography
                    text = model.dailyExpense
                )
            }
        }

        Divider(
            color = Color.DarkGray,
            thickness = 1.dp
        )

        Column(
            modifier = modifier
                .padding(3.dp)
                .fillMaxWidth()
        ) {
            model.transactions.forEach { item ->
                TransactionByCategory(
                    model = item,
                    backgroundColor = Color.Red,
                    icon = painterResource(id = R.drawable.default_icon_24),
                    descriptionIcon = "Headphones",
                    onTransactionClick = {}
                )
            }
        }
    }
}

@Composable
fun TransactionByCategory(
    modifier: Modifier = Modifier,
    model: UserTransactionModel,
    backgroundColor: Color,
    icon: Painter,
    descriptionIcon: String,
    onTransactionClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { onTransactionClick.invoke() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier
                .size(30.dp)
                .padding(end = 5.dp),
            tint = backgroundColor,
            painter = icon,
            contentDescription = descriptionIcon
        )
        Text(
            modifier = modifier
                .padding(start = 5.dp, end = 5.dp),
            text = model.category.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color.White
        )

        Text(
            modifier = modifier
                .padding(start = 5.dp, end = 5.dp),
            text = stringResource(id = R.string.category_divider),
            fontWeight = FontWeight(400),
            fontSize = 16.sp,
            color = Color.White
        )

        Text(
            modifier = modifier
                .padding(start = 5.dp),
            text = model.amount.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun TransactionViewNoIncomePreview() {
    TransactionsViewPreview(income = 0.0)
}

@Preview
@Composable
private fun TransactionViewWithIncomePreview() {
    TransactionsViewPreview(income = 1000.0)
}

@Composable
fun TransactionsViewPreview(
    income: Double
) {
    SafeMoneyTheme {
        Surface(

        ) {
//            TransactionsByDayView(
//                items = listOf(
//                    TransactionModel(
//                        amount = 223.0,
//                        category = "Food",
//                        subCategory = "7/11",
//                        emoji = painterResource(id = R.drawable.test_icon)
//                    ),
//                    TransactionModel(
//                        amount = 33.0,
//                        category = "Home",
//                        subCategory = "Bills",
//                        emoji = painterResource(id = R.drawable.test_icon)
//                    ),
//                    TransactionModel(
//                        amount = 243.0,
//                        category = "Work",
//                        subCategory = "Stuff",
//                        emoji = painterResource(id = R.drawable.test_icon)
//                    )
//                ),
//                totalByDay = TotalByDayModel(
//                    totalExpenseByDay = 1000000.0,
//                    totalIncomeByDay = income
//                )
//            )
        }
    }
}