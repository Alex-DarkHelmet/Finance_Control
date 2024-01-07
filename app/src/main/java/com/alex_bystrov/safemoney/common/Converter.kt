package com.alex_bystrov.safemoney.common

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class Converter {
    fun convertDateToYearAndMonth(date: String) : String {
        return LocalDate.parse(date).format(DateTimeFormatter.ofPattern("yyyy-MM"))
    }

    fun formattedMonth(month: String): String {
        return month.lowercase().replaceFirstChar { it.uppercase() }.slice(0..2)
    }

    fun formattedSum(unformattedInput: Double): String {
        val formatPattern =
            DecimalFormat("###,###,###,###,###,##0.00", DecimalFormatSymbols(Locale.CANADA))
        val formattedBalance = formatPattern.format(unformattedInput).replace(",", " ")

        return String.format(formattedBalance).trimEnd('0').removeSuffix(".")
    }

}