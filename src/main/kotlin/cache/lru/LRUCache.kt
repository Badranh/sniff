package cache.lru

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asDesktopBitmap
import cache.Cache



//this is from picasso/square, edited to fit our needs
/** A memory cache which uses a least-recently used eviction policy.  */
internal class LRUCache(override val maxByteCount: Int):Cache {

    /** Create a cache with a given maximum size in bytes.  */
    val cache =
        object : LRUCacheBaseDelegate<String, BitmapAndSize>(if (maxByteCount != 0) maxByteCount else 1) {
            override fun sizeOf(
                key: String,
                value: BitmapAndSize
            ): Int = value.byteCount.toInt()
        }

    override operator fun get(key: String): ImageBitmap? = cache[key]?.bitmap

    override operator fun set(
        key: String,
        bitmap: ImageBitmap
    ) {
        val byteCount = bitmap.asDesktopBitmap().computeByteSize()
        // If the bitmap is too big for the cache, don't even attempt to store it. Doing so will cause
        // the cache to be cleared. Instead just evict an existing element with the same key if it
        // exists.
        if (byteCount > maxSize()) {
            cache.remove(key)
            return
        }

        cache.put(key, BitmapAndSize(bitmap, byteCount))
    }

    override fun size(): Int = cache.size()


    override fun clear() = cache.evictAll()

    private fun maxSize(): Int = cache.maxSize()

    override fun clearKeyUri(uri: String) {
        // Keys are prefixed with a URI followed by '\n'.
        for (key in cache.snapshot().keys) {
            if (key.startsWith(uri) &&
                key.length > uri.length &&
                key[uri.length] ==  '\n'
            ) {
                cache.remove(key)
            }
        }
    }

    /** Returns the number of times [get] returned a value.  */
    override fun hitCount(): Int = cache.hitCount()

    /** Returns the number of times [get] returned `null`.  */
    override fun missCount(): Int = cache.missCount()

    /** Returns the number of times [set] was called.  */
    override fun putCount(): Int = cache.putCount()

    override fun evictionCount(): Int = cache.evictionCount()

    internal class BitmapAndSize(
        val bitmap: ImageBitmap,
        val byteCount: Long
    )
}