package loader

import androidx.compose.ui.graphics.ImageBitmap
import cache.Cache
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import service.SniffService
import service.entities.Either
import service.retryhandlers.backoffwithratio.retryIO
import service.services.KtorService
import service.services.bitmap


//check cache
//if not in cache
// request network , if success save cache
//else error

//use online and offline loader instead of function
class BasicLoader(override var cache: Cache, override var service: SniffService) : BaseLoader() {
    override suspend fun loadNetworkImage(url: String): ImageBitmap? {
        val offlineLoader = OfflineLoader(cache,service)
        val onlineLoader = OnlineLoader(cache,service)
        return offlineLoader.loadNetworkImage(url) ?: onlineLoader.loadNetworkImage(url)
    }

}