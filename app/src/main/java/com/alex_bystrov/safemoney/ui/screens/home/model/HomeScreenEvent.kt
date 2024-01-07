package com.alex_bystrov.safemoney.ui.screens.home.model

sealed class HomeScreenEvent {
    object EnterScreen : HomeScreenEvent()
    data class DetailTransaction(val id: Long) : HomeScreenEvent()

}
