package rest.dahlia.domain.manager

import android.content.Context
import android.os.Build
import androidx.annotation.StringRes
import rest.dahlia.R
import rest.dahlia.domain.manager.base.BasePreferenceManager

class PreferenceManager(ctx: Context): BasePreferenceManager(ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE)) {

    var theme by enumPreference("theme", Theme.SYSTEM)

    var dynamicColor by booleanPreference("dynamic_color", Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)

}

enum class Theme(@StringRes val nameRes: Int) {
    SYSTEM(R.string.theme_system),
    LIGHT(R.string.theme_light),
    DARK(R.string.theme_dark)
}