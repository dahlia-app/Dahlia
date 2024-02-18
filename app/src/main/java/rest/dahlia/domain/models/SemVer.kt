package rest.dahlia.domain.models

/**
 * A representation of the **Sem**antic **Ver**sioning format
 *
 * (major).(minor).(patch)
 *
 * @param major The major version number, this usually represents large revisions with many breaking changes
 * @param minor The minor version number, this usually comes with a small number of features or a few breaking changes
 * @param patch The path version number, this represents tiny changes - usually bug fixes or typos
 * @param prefixed Whether to prefix the string representation with a 'v'
 */
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

        /**
         * Parses the given [string][str] into a [SemVer] instance
         *
         * The input must contain 3 integers separated by a period (.), with the order being major, minor, then patch. It can optionally contain a single character prefix, usually 'v'
         *
         * Ex: 1.4.21
         *
         * @param str The string to convert
         * @param prefixed Whether the input contains a single character prefix
         *
         * @return [SemVer] The parsed SemVer, null if the input could not be parsed
         */
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