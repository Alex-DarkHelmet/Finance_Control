package com.alex_bystrov.safemoney.screens.home.model

sealed class HomeScreenEvent {
    object EnterScreen : HomeScreenEvent()
    data class DetailTransaction(val id: Long) : HomeScreenEvent()

}
