package loader.implementation

import cache.Cache
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import service.SniffService


import data.Result
import loader.BaseLoader

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