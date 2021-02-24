package sniff

import cache.Cache
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import loader.BaseLoader
import loader.BasicLoader
import loader.OfflineLoader
import loader.OnlineLoader
import service.SniffService
import service.services.KtorService


enum class LOADER {
    BASIC,OFFLINE,ONLINE
}
// use singleton value instance for type
class Sniff {
    companion object : ISniffFactory{
        override fun build(loaderType:LOADER,enableRetries: Boolean?, strategy: CachingStrategy?, service: SniffService?): BaseLoader {
            val cache = if(strategy!=null){
                 CacheBuilder withStrategy strategy
            }else{
                 CacheBuilder withStrategy  CachingStrategy.LRU
            }
            val httpService = service ?: KtorService(true)

            return when(loaderType){
                LOADER.BASIC ->{
                    BasicLoader(cache,httpService)
                }
                LOADER.OFFLINE->{
                    OfflineLoader(cache,httpService)
                }
                LOADER.ONLINE->{
                    OnlineLoader(cache,httpService)
                }
            }
        }
    }
}

