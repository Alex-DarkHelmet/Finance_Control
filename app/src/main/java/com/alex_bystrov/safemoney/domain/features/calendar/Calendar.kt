package com.alex_bystrov.safemoney.domain.features.calendar

import com.alex_bystrov.safemoney.common.Converter
import java.time.LocalDate

class Calendar(
    private val converter: Converter
) : CalendarRepository {

    override fun getStartWeekdayOfMonth(currentDate: String): String {
        val date = LocalDate.parse(currentDate)
        val startDayOfMonth = date.minusDays(date.dayOfMonth.toLong() - 1)
        return startDayOfMonth.dayOfWeek.name
    }

    override fun getMonthName(currentDate: String): String {
        val date = LocalDate.parse(currentDate)
        return converter.formattedMonth(month = date.month.name)
    }

    override fun getTotalDaysInMonth(currentDate: String): Int {
        val date = LocalDate.parse(currentDate)
        return date.month.maxLength()
    }

    override fun getNextMonth(currentMonth: Int): Int {
        return if (isLastMonth(month = currentMonth)) {
            FIRST_MONTH_NUMBER
        } else {
            currentMonth + 1
        }
    }

    override fun getLastMonth(currentMonth: Int): Int {
        return if (isFirstMonth(month = currentMonth)) {
            LAST_MONTH_NUMBER
        } else {
            currentMonth - 1
        }
    }

    override fun getLimitsDatesInMonth(date: String, isStart: Boolean): String {
        val currentDate = LocalDate.parse(date)

        return if (isStart) {
            currentDate.minusDays(currentDate.dayOfMonth.toLong() - 1).toString()
        } else {
            val daysInMonth = currentDate.lengthOfMonth()
            currentDate.plusDays(daysInMonth - currentDate.dayOfMonth.toLong()).toString()
        }
    }

    private fun isLastMonth(month: Int): Boolean = month == LAST_MONTH_NUMBER

    private fun isFirstMonth(month: Int): Boolean = month == FIRST_MONTH_NUMBER

    companion object {
        private const val FIRST_MONTH_NUMBER = 1
        private const val LAST_MONTH_NUMBER = 12
    }
}