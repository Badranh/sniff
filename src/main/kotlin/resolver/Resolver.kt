package resolver

import androidx.compose.ui.graphics.ImageBitmap
import cache.factory.CachingStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import data.Result
import kotlinx.coroutines.flow.StateFlow
import loader.BaseLoader
import service.SniffService
import sniff.LOADER

interface Resolver {
   var loader:BaseLoader
   suspend fun load(url:String):StateFlow<Result<ImageBitmap?>>
   fun changeLoader(loaderType : LOADER,strategy: CachingStrategy)
   fun changeHttpService(service: SniffService)
   fun changeCachingStrategy(strategy: CachingStrategy)
}