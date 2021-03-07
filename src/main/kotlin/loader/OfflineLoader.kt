package loader

import androidx.compose.ui.graphics.ImageBitmap
import cache.Cache
import data.Result
import kotlinx.coroutines.flow.flow
import service.SniffService

class OfflineLoader(override var cache: Cache, override var service: SniffService) : BaseLoader() {
    override suspend fun loadNetworkImage(url: String) = flow {
        println("ACCESSED CACHE")
        try {
            val image = cache[url] as ImageBitmap
            emit(Result.Success(image))
        } catch (e: Exception) {
            emit(Result.Failure("Not in cache"))
        }
    }

}