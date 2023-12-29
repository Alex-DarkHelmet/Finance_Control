package com.alex_bystrov.safemoney.domain.features.calculate.model

import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.domain.features.calculate.repository.CalculateInputRepository

enum class TypeOfCalculations {
    Sum, Minus, Divide, Multiply
}

class CalculateUserInput : CalculateInputRepository {

    private val converter = Converter()

    override fun getCalculatedInput(userInput: String): String {
        return calculateInput(userInput)
    }

    private fun calculateInput(userInput: String): String {
        var nextInputNum = ""
        var totalInput = ""
        val mathOperator = mutableListOf<Char>()

        for (item in userInput) {
            if (item.isDigit() || item == '.') {
                if (mathOperator.isEmpty()) {
                    totalInput += item.toString()
                } else {
                    nextInputNum += item.toString()
                }
            } else {
                if (mathOperator.isEmpty()) {
                    mathOperator.add(item)
                } else {
                    totalInput = inputCalculationsByType(
                        totalInput = totalInput.toDouble(),
                        operator = matchAllMathOperators(mathOperator.first()),
                        nexInputNumber = nextInputNum.toDouble()
                    )

                    mathOperator.clear()
                    mathOperator.add(item)
                    nextInputNum = ""
                }
            }
        }

        if (nextInputNum.isNotEmpty()) {
            totalInput = inputCalculationsByType(
                totalInput = totalInput.toDouble(),
                operator = matchAllMathOperators(mathOperator.first()),
                nexInputNumber = nextInputNum.toDouble()
            )
        }

        return converter.formattedSum(totalInput.toDouble())
    }

    private fun inputCalculationsByType(
        totalInput: Double, operator: TypeOfCalculations, nexInputNumber: Double
    ): String {
        return if (nexInputNumber != 0.0) {
            when (operator) {
                TypeOfCalculations.Sum -> totalInput.plus(nexInputNumber).toString()
                TypeOfCalculations.Minus -> totalInput.minus(nexInputNumber).toString()
                TypeOfCalculations.Multiply -> totalInput.times(nexInputNumber).toString()
                TypeOfCalculations.Divide -> totalInput.div(nexInputNumber).toString()
            }
        } else "0"
    }

    private fun matchAllMathOperators(charOperator: Char): TypeOfCalculations {
        return when (charOperator) {
            '+' -> TypeOfCalculations.Sum
            '-' -> TypeOfCalculations.Minus
            '*' -> TypeOfCalculations.Multiply
            '/' -> TypeOfCalculations.Divide
            else -> throw RuntimeException("Unknown operator - $charOperator")
        }
    }
}