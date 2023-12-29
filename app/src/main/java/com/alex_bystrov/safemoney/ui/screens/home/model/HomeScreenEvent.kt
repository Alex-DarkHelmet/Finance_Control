package com.alex_bystrov.safemoney.ui.screens.home.model

sealed class HomeScreenEvent {
    object EnterScreen : HomeScreenEvent()
    object DetailStatisticSavingByMonth : HomeScreenEvent()
    data class DetailTransaction(val id: Long) : HomeScreenEvent()
    //data class getCurrentStatistic(val currentMonth: String): HomeScreenEvent()
}
