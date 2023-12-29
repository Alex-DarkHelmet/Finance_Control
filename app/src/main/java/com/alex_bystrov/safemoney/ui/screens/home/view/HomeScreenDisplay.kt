package com.alex_bystrov.safemoney.ui.screens.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.ui.components.TransactionsByDayView
import com.alex_bystrov.safemoney.ui.screens.home.model.HomeScreenViewState

enum class BottomIcons {
    Home, Calendar, Budget, Statistic
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenDisplay(
    modifier: Modifier = Modifier,
    viewState: HomeScreenViewState.Display
) {
    val selected = remember {
        mutableStateOf(BottomIcons.Home)
    }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Black,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                title = {
                    Text(text = "")
                },
                navigationIcon = {
                    ModalNavigationDrawer(drawerContent = {
                        IconButton(onClick = {
                            TODO()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu_icon_24),
                                contentDescription = stringResource(id = R.string.menu_title),
                            )
                        }
                    }) {

                    }

                },
                actions = {
                    IconButton(onClick = {
                        TODO()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.repeat_transaction_icon_24),
                            contentDescription = stringResource(id = R.string.repeat_transaction_title)
                        )
                    }
                }
            )
        },
        floatingActionButton = {

        },
        bottomBar = {
            BottomAppBar(
                modifier = modifier
                    .fillMaxWidth(),
                containerColor = Color.Black,
                content = {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ItemBottomBar(
                            icon = if (selected.value == BottomIcons.Home) {
                                R.drawable.home_fill1_24
                            } else {
                                R.drawable.home_icon_24
                            },
                            description = stringResource(id = R.string.home_nav_bar),
                            isSelected = selected.value == BottomIcons.Home,
                            onItemClicked = {
                                selected.value = BottomIcons.Home
                            }
                        )

                        ItemBottomBar(
                            icon = if (selected.value == BottomIcons.Calendar) {
                                R.drawable.calendar_fill_24
                            } else {
                                R.drawable.calendar_icon_24
                            },
                            description = stringResource(id = R.string.calendar_nav_bar),
                            isSelected = selected.value == BottomIcons.Calendar,
                            onItemClicked = {
                                selected.value = BottomIcons.Calendar
                            }
                        )

                        ItemBottomBar(
                            icon = if (selected.value == BottomIcons.Budget) {
                                R.drawable.budget_fill_24
                            } else {
                                R.drawable.budget_icon_24
                            },
                            description = stringResource(id = R.string.budget_nav_bar),
                            isSelected = selected.value == BottomIcons.Budget,
                            onItemClicked = {
                                selected.value = BottomIcons.Budget
                            }
                        )

                        ItemBottomBar(
                            icon = if (selected.value == BottomIcons.Statistic) {
                                R.drawable.statistic_fill_24
                            } else {
                                R.drawable.statistic_icon_24
                            },
                            description = stringResource(id = R.string.statistic_nav_bar),
                            isSelected = selected.value == BottomIcons.Statistic,
                            onItemClicked = {
                                selected.value = BottomIcons.Statistic
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
        ) {
            item {
                TotalBalanceView(totalBalance = 200.4)

                Spacer(modifier = modifier.padding(bottom = 50.dp))
            }

            item {
                viewState.dailyTransactions.forEach { items ->
                    TransactionsByDayView(
                        date = items.date,
                        model = items
                    )
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun BottomNavBarPreview() {
//    HomeScreenDisplay()
//}