package rest.dahlia.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object Utils : KoinComponent {

    val ctx: Context by inject()

    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        prettyPrint = true
        encodeDefaults = true
    }

    val threadPool = Executors.newCachedThreadPool() as ExecutorService

    val navBarPadding: Dp
        @Composable get() = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()

}

/**
 * Runs the [block] in another thread, this should avoid freezing the UI
 */
fun thread(block: () -> Unit) = Utils.threadPool.execute(block)

/**
 * Shows a toast message with the given [string]
 */
fun showToast(string: String) = Toast.makeText(Utils.ctx, string, Toast.LENGTH_LONG).show()

/**
 * An instance of [WindowInsets] with no actual insets
 */
val WindowInsets.Companion.none get() = WindowInsets(0, 0, 0, 0)