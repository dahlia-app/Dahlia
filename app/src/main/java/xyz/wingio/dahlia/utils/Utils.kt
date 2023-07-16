package xyz.wingio.dahlia.utils

import android.content.Context
import android.os.Environment
import android.text.format.DateUtils
import android.widget.Toast
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.github.diamondminer88.zip.ZipEntry
import com.github.diamondminer88.zip.ZipReader
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object Utils : KoinComponent {

    val ctx: Context by inject()

    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
//        encodeDefaults = true
    }

    val threadPool = Executors.newCachedThreadPool() as ExecutorService

    val navBarPadding: Dp
        @Composable get() = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()

}

fun thread(block: () -> Unit) = Utils.threadPool.execute(block)

fun showToast(string: String) = Toast.makeText(Utils.ctx, string, Toast.LENGTH_LONG).show()

val projectDir = File(Environment.getExternalStorageDirectory(), "Dahlia/Projects")

inline fun <reified R> ZipReader.readObject(name: String): R {
    val entry = openEntry(name)!!
    val text = String(entry.read())
    return Utils.json.decodeFromString(text)
}

inline fun <reified R> ZipEntry.readObject(): R = Utils.json.decodeFromString(String(read()))

fun relativeTime(date: Instant): String {
    val now = Clock.System.now()
    val relativeTime = now - date
    return if(relativeTime.inWholeDays / 30 >= 2)
        formatDate(date, "MMM dd yyyy h:mma")
    else
        DateUtils.getRelativeTimeSpanString(
            /* time = */ date.toEpochMilliseconds(),
            /* now = */ now.toEpochMilliseconds(),
            /* minResolution = */ 0L,
            /* flags = */ DateUtils.FORMAT_ABBREV_ALL
        ).toString()
}

fun formatDate(date: Instant, format: String): String =
    SimpleDateFormat(format, Locale.getDefault())
        .format(Date(date.toEpochMilliseconds()))

val WindowInsets.Companion.none get() = WindowInsets(0, 0, 0, 0)