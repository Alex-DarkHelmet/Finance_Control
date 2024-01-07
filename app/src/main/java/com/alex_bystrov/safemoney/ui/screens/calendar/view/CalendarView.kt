package com.alex_bystrov.safemoney.ui.screens.calendar.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.domain.common.DailyTotalModel
import com.alex_bystrov.safemoney.domain.features.calendar.model.CalendarModel
import com.alex_bystrov.safemoney.domain.features.calendar.model.WeekDays
import com.alex_bystrov.safemoney.domain.features.calendar.Calendar
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyAppTheme
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyTheme
import java.time.LocalDate

private val converter = Converter()

@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    calendar: CalendarModel,
    dailyTotal: List<DailyTotalModel> = emptyList(),
    onPreviousMonthClicked: () -> Unit,
    onNextMonthClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(SafeMoneyAppTheme.colors.containerColor),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderCalendar(
                calendar = calendar,
                onPreviousMonthClicked = { onPreviousMonthClicked() },
                onNextMonthClicked = { onNextMonthClicked() }
            )

            Divider(
                color = Color.Black,
                thickness = 1.dp
            )

            TransactionsCalendar(
                calendar = calendar,
                dailyTotal = dailyTotal
            )
        }
    }
}

@Composable
private fun HeaderCalendar(
    modifier: Modifier = Modifier,
    calendar: CalendarModel,
    onPreviousMonthClicked: () -> Unit,
    onNextMonthClicked: () -> Unit,
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
            fontSize = 18.sp, // change to own typography
            fontWeight = FontWeight(500),
            color = Color.White, // change to own color
            text = "${calendar.month}  1 - ${calendar.days}"
        )

        IconButton(
            onClick = { onPreviousMonthClicked() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back_24),
                contentDescription = stringResource(id = R.string.previous_month),
                tint = SafeMoneyAppTheme.colors.primaryText
            )
        }

        IconButton(onClick = { onNextMonthClicked() })
        {
            Icon(
                painter = painterResource(id = R.drawable.arrow_forward_24),
                contentDescription = stringResource(id = R.string.previous_month),
                tint = SafeMoneyAppTheme.colors.primaryText
            )
        }
    }
}

@Composable
private fun WeekDaysHeader(
    modifier: Modifier = Modifier,
    weekDay: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = modifier
                .padding(horizontal = 2.dp, vertical = 2.dp),
            colors = CardDefaults.cardColors(SafeMoneyAppTheme.colors.containerColor),
            shape = ShapeDefaults.Small
        ) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = modifier
                        .padding(5.dp),
                    text = weekDay,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = SafeMoneyAppTheme.colors.primaryText
                )
            }
        }

    }
}

@Composable
private fun TransactionsCalendar(
    modifier: Modifier = Modifier,
    calendar: CalendarModel,
    dailyTotal: List<DailyTotalModel> = emptyList(),
) {
    val days = (1..calendar.days).map { it.toString() }

    val chosenDay = remember {
        mutableIntStateOf(LocalDate.now().dayOfMonth)
    }

    LazyVerticalGrid(
        modifier = modifier
            .padding(),
        columns = GridCells.Fixed(calendar.weekDays.size),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            items(calendar.weekDays.size) { day ->
                WeekDaysHeader(weekDay = calendar.weekDays[day].toString())
            }

            items(calendar.startWeekday) {
                TransactionItemByDay(
                    onDayClicked = {}
                )
            }
            items(days.size) { day ->
                val incomeByDay = dailyTotal.find { incomes ->
                    days[day] == incomes.date.takeLast(2).trimStart('0')
                }

                val expenseByDay = dailyTotal.find { expenses ->
                    days[day] == expenses.date.takeLast(2).trimStart('0')
                }

                TransactionItemByDay(
                    day = days[day],
                    income = incomeByDay?.dailyIncome,
                    expense = expenseByDay?.dailyExpense,
                    isSelectedDay = chosenDay.intValue.toString() == days[day],
                    isCurrentDay = calendar.currentDay.toString() == days[day],
                    onDayClicked = {
                        chosenDay.intValue = days[day].toInt()
                    }
                )
            }
        }
    )
}

@Composable
private fun TransactionItemByDay(
    modifier: Modifier = Modifier,
    day: String? = null,
    income: String? = null,
    expense: String? = null,
    isCurrentDay: Boolean? = false,
    onDayClicked: () -> Unit,
    isSelectedDay: Boolean = false
) {
    Card(
        modifier = modifier
            .padding(vertical = 2.dp)
            .clickable {
                onDayClicked()
            },
        colors = if (isSelectedDay) {
            CardDefaults.cardColors(SafeMoneyAppTheme.colors.tintChosenDay)
        } else {
            CardDefaults.cardColors(SafeMoneyAppTheme.colors.containerColor)
        },
        shape = ShapeDefaults.Small
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier,
                color = if (isCurrentDay == true) {
                    SafeMoneyAppTheme.colors.currentDayColor
                } else {
                    SafeMoneyAppTheme.colors.primaryText
                },
                fontWeight = FontWeight(400),
                text = day ?: ""
            )

            Text(
                modifier = modifier,
                color = SafeMoneyAppTheme.colors.incomeText,
                fontSize = 10.sp,
                text = income ?: ""
            )

            Text(
                modifier = modifier,
                color = SafeMoneyAppTheme.colors.expenseText,
                fontSize = 10.sp,
                text = expense ?: ""
            )
        }
    }
}

@Preview
@Composable
fun CalendarTransactionViewPreview() {
    SafeMoneyTheme {
        Surface(
            color = SafeMoneyAppTheme.colors.primaryBackground
        ) {
            CalendarView(
                calendar = CalendarModel(
                    currentDay = LocalDate.now().dayOfMonth,
                    month = converter.formattedMonth(LocalDate.now().month.name),
                    days = LocalDate.now().month.maxLength(),
                    weekDays = WeekDays.values().toList(),
                    startWeekday = Calendar(converter).getStartWeekdayOfMonth(LocalDate.now().toString()),
                ),
                dailyTotal = listOf(
                    DailyTotalModel(
                        date = "2020.05.13",
                        dailyExpense = "200.0",
                        dailyIncome = "20.0"
                    ),
                    DailyTotalModel(
                        date = "2020.05.16",
                        dailyIncome = "22340.0",
                        dailyExpense = "34560.0"
                    ),
                    DailyTotalModel(
                        date = "2020.05.18",
                        dailyIncome = "23450.0",
                        dailyExpense = "85780.0"
                    ),
                ),
                onNextMonthClicked = {

                },
                onPreviousMonthClicked = {
                }
            )
        }
    }
}