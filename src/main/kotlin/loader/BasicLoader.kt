package loader

import androidx.compose.ui.graphics.ImageBitmap
import cache.Cache
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import io.ktor.utils.io.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import service.SniffService
import service.entities.Either
import service.retryhandlers.backoffwithratio.retryIO
import service.services.KtorService
import service.services.bitmap


import data.Result
//check cache
//if not in cache
// request network , if success save cache
//else error

//add loading,success,error

class BasicLoader(override var cache: Cache, override var service: SniffService) : BaseLoader() {
    override suspend fun loadNetworkImage(url: String) = flow {
        emit(Result.Loading(0))
        OfflineLoader(cache,service).loadNetworkImage(url).collect {
            emit(it)
        }
        OnlineLoader(cache,service).loadNetworkImage(url).collect {
            emit(it)
        }
    }

}