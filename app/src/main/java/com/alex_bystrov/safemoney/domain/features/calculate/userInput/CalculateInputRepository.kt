package com.alex_bystrov.safemoney.domain.features.calculate.userInput

interface CalculateInputRepository {
    fun getCalculatedInput(userInput: String): String
}