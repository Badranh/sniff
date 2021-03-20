package sniff

import cache.factory.CachingStrategy
import resolver.Resolver
import service.SniffService


interface ISniffFactory {
    fun build(loaderType:LOADER,enableRetries: Boolean?=null, strategy: CachingStrategy?=null, service: SniffService?=null): Resolver
}