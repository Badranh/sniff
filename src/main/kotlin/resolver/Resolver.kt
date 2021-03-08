package resolver

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import data.Result
import kotlinx.coroutines.flow.StateFlow

interface Resolver {
   suspend fun load(url:String):StateFlow<Result<ImageBitmap?>>
}