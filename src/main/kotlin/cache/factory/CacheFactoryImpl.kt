package cache.factory

import cache.Cache
import cache.lru.LRUCache


class CacheBuilder {

    companion object: ICacheFactory {
        override fun createCache(strategy: CachingStrategy):Cache {
            return when(strategy){
                CachingStrategy.LRU ->{
                    LRUCache(400000000)
                }
                CachingStrategy.TIMED ->{
                    LRUCache(400000000)
                }
            }
        }
    }

}

infix fun CacheBuilder.Companion.withStrategy(strategy: CachingStrategy):Cache{
    return createCache(strategy)
}
