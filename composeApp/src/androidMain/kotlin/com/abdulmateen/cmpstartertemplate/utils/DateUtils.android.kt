package com.abdulmateen.cmpstartertemplate.utils

import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.text.format

actual fun formatDatePlatform(
    date: Instant
): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateInLocalTimeZone = Date(date.toEpochMilliseconds())
    return formatter.format(dateInLocalTimeZone)
}