package cache.factory

import cache.Cache


public enum class CachingStrategy{
    LRU,TIMED
}
interface ICacheFactory {
    fun createCache(strategy:CachingStrategy,):Cache
}