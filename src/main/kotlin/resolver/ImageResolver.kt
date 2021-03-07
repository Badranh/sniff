package resolver

import androidx.compose.ui.graphics.ImageBitmap
import data.Result
import extensions.doOnFailure
import extensions.doOnSuccess
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import loader.BaseLoader

class ImageResolver(private val loader:BaseLoader) : Resolver {

    private val globalScope = GlobalScope
    private val internalFlows = HashMap<String,StateFlow<Result<ImageBitmap?>>>()

    override suspend fun load(url: String): StateFlow<Result<ImageBitmap?>> {
        internalFlows[url] = internalFlows[url] ?: loader.loadNetworkImage(url).stateIn(globalScope, SharingStarted.WhileSubscribed(),Result.Loading(0))
        val result = internalFlows[url]
        internalFlows.remove(url)
        return result!!
    }
}