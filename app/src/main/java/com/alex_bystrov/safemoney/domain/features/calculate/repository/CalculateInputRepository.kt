package com.alex_bystrov.safemoney.domain.features.calculate.repository

interface CalculateInputRepository {
    fun getCalculatedInput(userInput: String): String
}