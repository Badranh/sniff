package sniff

import cache.Cache
import cache.factory.CachingStrategy
import loader.BaseLoader
import loader.BasicLoader
import service.SniffService
import service.services.KtorService


interface ISniffFactory {
    fun build(loaderType:LOADER,enableRetries: Boolean?=null, strategy: CachingStrategy?=null, service: SniffService?=null): BaseLoader
}