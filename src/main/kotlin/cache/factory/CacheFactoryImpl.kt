package cache.factory

import cache.Cache
import cache.base.BaseCache
import cache.lru.LRUCache


class CacheBuilder {

    companion object: ICacheFactory {
        override fun createCache(strategy: CachingStrategy):Cache {
            return when(strategy){
                CachingStrategy.LRU ->{
                    LRUCache(createBaseCache())
                }
                CachingStrategy.TIMED ->{
                    LRUCache(createBaseCache())
                }
            }
        }
        private fun createBaseCache()= BaseCache()
    }

}

infix fun CacheBuilder.Companion.withStrategy(strategy: CachingStrategy):Cache{
    return createCache(strategy)
}
