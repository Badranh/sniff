package cache

import androidx.compose.ui.graphics.ImageBitmap

//extracted to module soon
interface Cache {
    val maxByteCount: Int
    /** Returns item if found in cache.  */
    operator fun get(key: String): ImageBitmap?
    /** Clears the whole cache.  */
    fun clear()
    /** Returns the number of values that have been evicted.  */
    fun evictionCount():Int
    /** Returns the number of times [set] was called.  */
    fun putCount(): Int
    /** Returns the number of times [get] returned `null`.  */
    fun missCount(): Int
    /** Returns the number of times [get] returned a value.  */
    fun hitCount(): Int
    /** set item in cache.  */
    operator fun set(key: String, bitmap: ByteArray)
    /** Returns cache size.  */
    fun size(): Int
    /** Removes item from cache if found. */
    fun clearKeyUri(uri: String)
}