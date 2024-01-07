package com.alex_bystrov.safemoney.domain.features.calendar

import com.alex_bystrov.safemoney.common.Converter
import com.alex_bystrov.safemoney.domain.features.calendar.model.WeekDays
import java.time.LocalDate
import java.util.Calendar

class Calendar(
    private val converter: Converter
) : CalendarRepository {
    override fun getStartWeekdayOfMonth(currentDate: String): Int {
        val date = LocalDate.parse(currentDate)
        val startDayOfMonth = date.minusDays(date.dayOfMonth.toLong() - 1)
        val weekdays = WeekDays.values()
        return weekdays[startDayOfMonth.dayOfMonth].ordinal - 1
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

    override fun getFirstAndLastDayInMonth(date: String, isFirst: Boolean): String {
        val currentDate = LocalDate.parse(date)

        return if (isFirst) {
            currentDate.minusDays(currentDate.dayOfMonth.toLong() - 1).toString()
        } else {
            val daysInMonth = currentDate.lengthOfMonth()
            currentDate.plusDays(daysInMonth - currentDate.dayOfMonth.toLong()).toString()
        }
    }

    override fun isFirstDayOfMonth(date: String): Boolean {
        val currentDate = LocalDate.parse(date).dayOfMonth

        return currentDate == FIRST_DAY_IN_MONTH
    }

    private fun isLastMonth(month: Int): Boolean = month == LAST_MONTH_NUMBER

    private fun isFirstMonth(month: Int): Boolean = month == FIRST_MONTH_NUMBER

    companion object {
        private const val FIRST_MONTH_NUMBER = 1
        private const val LAST_MONTH_NUMBER = 12
        private const val FIRST_DAY_IN_MONTH = 1
    }
}