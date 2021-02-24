package cache

//extracted to module soon
interface Cache {
    val size: Int

    operator fun set(key: Any, value: Any)

    operator fun get(key: Any): Any?

    fun remove(key: Any): Any?

    fun clear()
}