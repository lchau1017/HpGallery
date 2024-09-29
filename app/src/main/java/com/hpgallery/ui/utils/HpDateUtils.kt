package com.hpgallery.ui.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object HpDateUtils {
    // Constants for date formats and patterns
    private const val INPUT_DATE_FORMAT = "dd-MM-yyyy"
    private const val OUTPUT_DATE_FORMAT = "dd MMM yyyy"
    private const val UNKNOWN = "Unknown"

    private val OUTPUT_PATTERN_REGEX = Regex("^\\d{2} [A-Za-z]{3} \\d{4}$")
    private val INPUT_PATTERN_REGEX = Regex("^\\d{2}-\\d{2}-\\d{4}$")

    fun formatDate(dateString: String?): String {
        return try {
            // Check if dateString is null or empty
            if (dateString.isNullOrEmpty()) return UNKNOWN

            // If the date string is already in the output format, return it as is
            if (OUTPUT_PATTERN_REGEX.matches(dateString)) {
                return dateString
            }

            // If the date string does not match the input format, return "Unknown"
            if (!INPUT_PATTERN_REGEX.matches(dateString)) {
                return UNKNOWN
            }

            // Parse the input date string
            val inputDateFormat = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.getDefault())
            inputDateFormat.isLenient = false // Ensures strict date parsing
            val date = inputDateFormat.parse(dateString)

            // Format the date to the output format
            val outputDateFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault())
            date?.let { outputDateFormat.format(it) } ?: UNKNOWN
        } catch (e: ParseException) {
            UNKNOWN
        }
    }
}