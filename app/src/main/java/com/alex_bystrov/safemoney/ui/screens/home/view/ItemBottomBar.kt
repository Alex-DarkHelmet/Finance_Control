package com.alex_bystrov.safemoney.ui.screens.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyAppTheme
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyTheme


@Composable
fun ItemBottomBar(
    modifier: Modifier = Modifier,
    icon: Int,
    description: String,
    onItemClicked: () -> Unit,
    isSelected: Boolean
) {
    IconButton(
        modifier = modifier
            .size(70.dp),
        colors = IconButtonDefaults.iconButtonColors(Color.Black),
        onClick = {
            onItemClicked()
        },
        content = {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = modifier
                        .size(25.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    painter = painterResource(id = icon),
                    contentDescription = description,
                    tint = if (isSelected) {
                        SafeMoneyAppTheme.colors.primaryText
                    } else {
                        SafeMoneyAppTheme.colors.secondaryText
                    }
                )
                Text(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally),
                    text = description,
                    color = if (isSelected) {
                        SafeMoneyAppTheme.colors.primaryText
                    } else {
                        SafeMoneyAppTheme.colors.secondaryText
                    },
                    fontWeight = FontWeight(400),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeItemPreview() {
    ItemBottomPreview(
        icon = R.drawable.home_icon_24,
        description = "Home"
    )
}

@Preview
@Composable
fun CalendarItemPreview() {
    ItemBottomPreview(
        icon = R.drawable.calendar_icon_24,
        description = "Date"
    )
}

@Preview
@Composable
fun BudgetItemPreview() {
    ItemBottomPreview(
        icon = R.drawable.budget_icon_24,
        description = "Budget"
    )
}

@Preview
@Composable
fun StatisticItemPreview() {
    ItemBottomPreview(
        icon = R.drawable.statistic_icon_24,
        description = "Statistic"
    )
}

@Composable
private fun ItemBottomPreview(
    icon: Int,
    description: String
) {
    SafeMoneyTheme {
        Surface() {
            ItemBottomBar(
                icon = icon,
                description = description,
                isSelected = false,
                onItemClicked = {}
            )
        }
    }
}