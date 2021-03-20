package resolver

import androidx.compose.ui.graphics.ImageBitmap
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import data.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import loader.BaseLoader
import loader.implementation.BasicLoader
import loader.implementation.OfflineLoader
import loader.implementation.OnlineLoader
import service.SniffService
import service.services.KtorService
import sniff.LOADER

class ImageResolver(override var loader: BaseLoader) : Resolver {

    private val globalScope = GlobalScope
    private val internalFlows = HashMap<String,StateFlow<Result<ImageBitmap?>>>()

    override suspend fun load(url: String): StateFlow<Result<ImageBitmap?>> {
        internalFlows[url] = internalFlows[url] ?: loader.loadNetworkImage(url).stateIn(globalScope, SharingStarted.WhileSubscribed(),Result.Loading(0))
        val result = internalFlows[url]
        println("Removed")
        return result!!
    }


    //Research for the edge cases that may happen
    override fun changeLoader(loaderType: LOADER, strategy: CachingStrategy) {
        val cache = CacheBuilder withStrategy strategy
        val httpService = KtorService(true)
        loader = when(loaderType){
            LOADER.BASIC -> {
                BasicLoader(cache,httpService)
            }
            LOADER.ONLINE -> {
                OnlineLoader(cache,httpService)
            }
            LOADER.OFFLINE ->{
                OfflineLoader(cache,httpService)
            }
        }
    }

    override fun changeHttpService(service: SniffService) {

    }

    override fun changeCachingStrategy(strategy: CachingStrategy) {

    }


}