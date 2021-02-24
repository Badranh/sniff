package loader

import androidx.compose.ui.graphics.ImageBitmap
import cache.Cache
import service.SniffService
import service.entities.Either
import service.services.bitmap

class OnlineLoader(override var cache: Cache, override var service: SniffService) : BaseLoader() {
    override suspend fun loadNetworkImage(url: String): ImageBitmap? {
        return when(val resp = service.downloadImage(url)){
            is Either.Right ->{
                val respone = resp.r.bitmap()
                cache[url] = respone
                println("SUCCESS From Network")
                respone

            }
            is Either.Left ->{
                println("ERROR From Network")
                null
            }
        }
    }
}