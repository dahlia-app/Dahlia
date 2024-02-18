package rest.dahlia.utils

import android.text.format.DateUtils
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Gets a relative time string for the duration between now and [date]
 */
fun relativeTime(date: Instant): String {
    val now = Clock.System.now()
    val relativeTime = now - date
    return if (relativeTime.inWholeDays / 30 >= 2)
        formatDate(date, "MMM dd yyyy h:mma")
    else
        DateUtils.getRelativeTimeSpanString(
            /* time = */ date.toEpochMilliseconds(),
            /* now = */ now.toEpochMilliseconds(),
            /* minResolution = */ 0L,
            /* flags = */ DateUtils.FORMAT_ABBREV_ALL
        ).toString()
}

/**
 * Formats the given [date] using the specified [format]
 *
 * @throws IllegalArgumentException if the given format pattern is invalid
 */
fun formatDate(date: Instant, format: String): String =
    SimpleDateFormat(format, Locale.getDefault())
        .format(Date(date.toEpochMilliseconds()))