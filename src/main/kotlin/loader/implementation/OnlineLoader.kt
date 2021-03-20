package loader.implementation

import cache.Cache
import kotlinx.coroutines.flow.flow
import service.SniffService
import service.entities.Either
import service.services.bitmap
import data.Result
import loader.BaseLoader

class OnlineLoader(override var cache: Cache, override var service: SniffService) : BaseLoader() {
    override suspend fun loadNetworkImage(url: String) = flow {
        when(val resp = service.downloadImage(url)){
            is Either.Right ->{
                val respone = resp.r.bitmap()
                cache[url] = respone
                println("NETWORK")
                emit(Result.Success(respone))

            }
            is Either.Left ->{
                emit(Result.Failure(resp.l))
            }
        }
    }
}