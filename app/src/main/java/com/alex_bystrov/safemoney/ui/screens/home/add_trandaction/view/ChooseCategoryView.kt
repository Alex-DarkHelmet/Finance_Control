package com.alex_bystrov.safemoney.ui.screens.home.add_trandaction.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex_bystrov.safemoney.domain.common.CategoryModel
import com.alex_bystrov.safemoney.domain.features.transactions.models.TypeTransactionModel

@Composable
fun ChooseCategoryView(
    modifier: Modifier = Modifier,
    model: CategoryModel
) {

}

// change all colors
@Composable
private fun TypeTransactionSwitch(
    modifier: Modifier = Modifier,
    typeTransaction: TypeTransactionModel
) {
    var type by remember {
        mutableStateOf(typeTransaction)
    }

    val background by animateColorAsState(
        targetValue = if (type == TypeTransactionModel.Income) {
            Color.Red
        } else {
            Color.DarkGray
        },
        animationSpec = tween(
            durationMillis = 2000,
            delayMillis = 40,
            easing = LinearEasing
        ),
        label = "background"
    )

    Box(
        modifier = modifier
            .clip(RectangleShape)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.DarkGray),
    ) {
        Row(
            modifier = modifier
                .padding(2.dp),
        ) {
            Button(
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(background),
                onClick = {
                    type = TypeTransactionModel.Expense
                }
            ) {
                Text(
                    color = if (type == TypeTransactionModel.Expense) {
                        Color.White
                    } else {
                        Color.LightGray
                    },
                    text = "Expense"
                )
            }
            Button(
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(background),
                onClick = {
                    type = TypeTransactionModel.Income
                }
            ) {
                Text(
                    color = if (type == TypeTransactionModel.Income) {
                        Color.White
                    } else {
                        Color.LightGray
                    },
                    text = "Income"
                )
            }
        }
    }
}

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    model: List<CategoryModel>
) {
    Row(
        content = {
            model.forEach { item ->
                CategoryIcon(name = item.category, icon = item.icon)
            }
        }
    )
}

@Composable
fun CategoryIcon(
    modifier: Modifier = Modifier,
    name: String,
    icon: Int
) {
    Column(
        modifier = modifier
            .padding(4.dp),
    ) {
        IconButton(
            modifier = modifier
                .clip(CircleShape)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = name
            )
        }

        Text(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(2.dp),
            color = Color.White,
            fontSize = 12.sp,
            text = name
        )
    }
}

//@Preview
//@Composable
//fun CategoryIcon_Preview() {
//    CategoryIcon(name = "Food", icon = R.drawable.default_icon_24)
//}

@Preview
@Composable
fun TypeTransactionSwitch_Preview() {
    TypeTransactionSwitch(typeTransaction = TypeTransactionModel.Expense)
}

//@Preview
//@Composable
//fun Category_Preview() {
//    CategoryList(model = CategoryModel.baseExpenseCategoryList)
//}