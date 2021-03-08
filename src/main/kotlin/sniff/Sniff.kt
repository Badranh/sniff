package sniff

import cache.Cache
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import loader.BaseLoader
import loader.BasicLoader
import loader.OfflineLoader
import loader.OnlineLoader
import resolver.ImageResolver
import resolver.Resolver
import service.SniffService
import service.services.KtorService


enum class LOADER {
    BASIC,OFFLINE,ONLINE
}
// use singleton value instance for type
class Sniff {
    companion object : ISniffFactory{
        private lateinit var instance: Resolver
        override fun build(loaderType:LOADER,enableRetries: Boolean?, strategy: CachingStrategy?, service: SniffService?): Resolver {
            if(!::instance.isInitialized){
                val cache = if(strategy!=null){
                    CacheBuilder withStrategy strategy
                }else{
                    CacheBuilder withStrategy  CachingStrategy.LRU
                }
                val httpService = service ?: KtorService(true)
                instance = when(loaderType){
                    LOADER.BASIC ->{
                        ImageResolver(BasicLoader(cache,httpService))
                    }
                    LOADER.OFFLINE->{
                        ImageResolver(OfflineLoader(cache,httpService))
                    }
                    LOADER.ONLINE->{
                        ImageResolver(OnlineLoader(cache,httpService))
                    }
                }
            }
            return instance
        }
    }
}

