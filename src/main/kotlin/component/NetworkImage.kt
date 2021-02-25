package component

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.ImagePainter
import androidx.compose.ui.layout.ContentScale
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import loader.BasicLoader
import service.services.KtorService
import sniff.LOADER
import sniff.Sniff

//fix component for variable url

@Composable
fun NetworkImage(
    url:String,
    scope:CoroutineScope,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
){
    val loader by remember{ mutableStateOf(Sniff.build(LOADER.BASIC))}
    var imageBitmap by remember{ mutableStateOf<ImageBitmap?>(null) }
        scope.launch {
            println("in scope")
            imageBitmap = loader.loadNetworkImage(url)!!
        }
        onDispose {
            scope.cancel("Canceled")
        }

    if(imageBitmap!=null) {
        Image(
            painter = ImagePainter(imageBitmap!!) ,
            modifier = modifier,
            alignment = alignment,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )
    }else{
        //place holder
    }
}