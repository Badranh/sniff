package cache.base

import cache.Cache

class BaseCache : Cache {
    private val cache = HashMap<Any, Any>()

    override val size: Int
        get() = cache.size

    override fun set(key: Any, value: Any) {
        this.cache[key] = value
    }

    override fun remove(key: Any) = this.cache.remove(key)

    override fun get(key: Any) = this.cache[key]

    override fun clear() = this.cache.clear()
}