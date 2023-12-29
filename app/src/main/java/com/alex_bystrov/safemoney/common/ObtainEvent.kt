package com.alex_bystrov.safemoney.common

interface ObtainEvent<E> {
    fun obtainEvent(event: E)
}