package rest.dahlia.utils

import kotlinx.serialization.*
import com.github.diamondminer88.zip.ZipEntry
import com.github.diamondminer88.zip.ZipReader

/**
 * Reads an entry as text and deserializes it into type [R]
 *
 * @param path The path to an entry in the zip file
 *
 * @throws SerializationException in case of any decoding-specific error
 * @throws IllegalArgumentException if the decoded input is not a valid instance of [R]
 *
 * @return [R] - The deserialized entry
 */
inline fun <reified R> ZipReader.readObject(path: String): R {
    val entry = openEntry(path)!!
    val text = String(entry.read())
    return Utils.json.decodeFromString(text)
}

/**
 * Reads the entry as text and deserializes it into type [R]
 *
 * @throws SerializationException in case of any decoding-specific error
 * @throws IllegalArgumentException if the decoded input is not a valid instance of [R]
 *
 * @return [R] - The deserialized entry
 */
inline fun <reified R> ZipEntry.readObject(): R = Utils.json.decodeFromString(String(read()))

/**
 * Reads a zip entry as text
 *
 * @param path The path to an entry in the zip file
 */
fun ZipReader.readString(path: String) = openEntry(path)?.read()?.let { String(it) }