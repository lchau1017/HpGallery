package com.hpgallery.ui

import org.junit.Assert.assertEquals
import org.junit.Test

class HpDateUtilsTest {

    @Test
    fun `given date string in output format when formatDate is called then return the original date string`() {
        // Given
        val inputDate = "31 Jul 1980"

        // When
        val result = HpDateUtils.formatDate(inputDate)

        // Then
        assertEquals("31 Jul 1980", result)
    }

    @Test
    fun `given valid date string in input format when formatDate is called then return formatted date`() {
        // Given
        val inputDate = "31-07-1980"

        // When
        val result = HpDateUtils.formatDate(inputDate)

        // Then
        assertEquals("31 Jul 1980", result)
    }

    @Test
    fun `given null date string when formatDate is called then return Unknown`() {
        // Given
        val inputDate: String? = null

        // When
        val result = HpDateUtils.formatDate(inputDate)

        // Then
        assertEquals("Unknown", result)
    }

    @Test
    fun `given empty date string when formatDate is called then return Unknown`() {
        // Given
        val inputDate = ""

        // When
        val result = HpDateUtils.formatDate(inputDate)

        // Then
        assertEquals("Unknown", result)
    }

    @Test
    fun `given invalid date format when formatDate is called then return Unknown`() {
        // Given
        val inputDate = "1980-07-31" // Incorrect format

        // When
        val result = HpDateUtils.formatDate(inputDate)

        // Then
        assertEquals("Unknown", result)
    }

    @Test
    fun `given invalid date in correct format when formatDate is called then return Unknown`() {
        // Given
        val inputDate = "31-02-1980" // Invalid date (February 31st doesn't exist)

        // When
        val result = HpDateUtils.formatDate(inputDate)

        // Then
        assertEquals("Unknown", result)
    }
}
