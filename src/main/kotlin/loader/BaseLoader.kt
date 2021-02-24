package loader

import androidx.compose.ui.graphics.ImageBitmap
import cache.Cache
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import service.SniffService
import service.services.KtorService

abstract class BaseLoader {
    abstract var cache : Cache
    abstract var service : SniffService

    abstract suspend fun loadNetworkImage(url:String): ImageBitmap?
}