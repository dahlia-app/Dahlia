package xyz.wingio.dahlia.utils

data class SemVer(
    val major: Int,
    val minor: Int,
    val patch: Int,
    private val prefixed: Boolean = false
) : Comparable<SemVer> {

    override fun compareTo(other: SemVer): Int {
        val pairs = arrayOf(
            major to other.major,
            minor to other.minor,
            patch to other.patch
        )

        return pairs
            .map { (first, second) -> first.compareTo(second) }
            .find { it != 0 }
            ?: 0
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is SemVer -> major == other.major && minor == other.minor && patch == other.patch
            else -> false
        }
    }

    override fun toString() = "${if (prefixed) "v" else ""}$major.$minor.$patch"

    override fun hashCode(): Int {
        var result = major
        result = 31 * result + minor
        result = 31 * result + patch
        return result
    }

    companion object {

        fun fromString(str: String, prefixed: Boolean = false): SemVer? {
            if (prefixed && str.firstOrNull() != 'v')
                return null

            val version = if (prefixed) str.substring(1) else str
            val parts = version
                .split(".")
                .mapNotNull { it.toIntOrNull() }
                .takeIf { it.size == 3 }
                ?: return null

            return SemVer(parts[0], parts[1], parts[2], prefixed)
        }

    }

}