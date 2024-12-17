package com.abdulmateen.cmpstartertemplate.core.utils

import kotlinx.datetime.Instant
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.localTimeZone

actual fun formatDatePlatform(date: Instant): String
{
    val formatter = NSDateFormatter()
    formatter.dateFormat = "dd/MM/yyyy"
    formatter.timeZone = NSTimeZone.localTimeZone
    return formatter.stringFromDate(NSDate.dateWithTimeIntervalSince1970(date.epochSeconds.toDouble()))
}