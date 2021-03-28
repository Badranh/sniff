package cache.factory

import cache.Cache
import cache.lru.LRUCache
import cache.lru.LRUDiskCache


class CacheBuilder {

    companion object: ICacheFactory {
        override fun createCache(strategy: CachingStrategy):Cache {
            return when(strategy){
                CachingStrategy.LRU ->{
                    LRUDiskCache(400000000)
                }
                CachingStrategy.TIMED ->{
                    LRUDiskCache(400000000)
                }
            }
        }
    }

}

infix fun CacheBuilder.Companion.withStrategy(strategy: CachingStrategy):Cache{
    return createCache(strategy)
}
