package loader

import androidx.compose.ui.graphics.ImageBitmap
import cache.Cache
import kotlinx.coroutines.flow.Flow
import service.SniffService
import data.Result

abstract class BaseLoader {
    abstract var cache : Cache
    abstract var service : SniffService

    abstract suspend fun loadNetworkImage(url:String): Flow<Result<ImageBitmap?>>
}