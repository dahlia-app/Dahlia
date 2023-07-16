package xyz.wingio.dahlia.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

data class Padding(
    val top: Dp = 0.dp,
    val start: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val end: Dp = 0.dp
) : PaddingValues {

    constructor(all: Dp = 0.dp) : this(all, all, all, all)

    constructor(horizontal: Dp = 0.dp, vertical: Dp = 0.dp) : this(vertical, horizontal, vertical, horizontal)

    override fun calculateLeftPadding(layoutDirection: LayoutDirection) =
        if (layoutDirection == LayoutDirection.Ltr) start else end

    override fun calculateTopPadding() = top

    override fun calculateRightPadding(layoutDirection: LayoutDirection) =
        if (layoutDirection == LayoutDirection.Ltr) end else start

    override fun calculateBottomPadding() = bottom

    override fun equals(other: Any?): Boolean {
        if (other !is Padding) return false
        return start == other.start &&
                top == other.top &&
                end == other.end &&
                bottom == other.bottom
    }

    override fun hashCode() =
        ((start.hashCode() * 31 + top.hashCode()) * 31 + end.hashCode()) * 31 + bottom.hashCode()
}

val Dp.padding get() = Padding(this)