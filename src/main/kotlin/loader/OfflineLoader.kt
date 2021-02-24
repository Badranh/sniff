package loader

import androidx.compose.ui.graphics.ImageBitmap
import cache.Cache
import service.SniffService

class OfflineLoader(override var cache: Cache, override var service: SniffService) : BaseLoader() {
    override suspend fun loadNetworkImage(url: String): ImageBitmap? {
        return try {
            println("Requesting From CACHE")
            val image = cache[url] as ImageBitmap
            println("SUCCESS From CACHE")
            image
        } catch (e: Exception) {
            println("ERROR From CACHE")
            null
        }
    }
}