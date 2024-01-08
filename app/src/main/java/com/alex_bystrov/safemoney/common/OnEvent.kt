package com.alex_bystrov.safemoney.common

interface OnEvent<E> {
    fun onEvent(event: E)
}