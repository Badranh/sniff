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
import extensions.doOnFailure
import extensions.doOnLoading
import extensions.doOnSuccess
import extensions.shareInScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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


    onCommit(url){
        scope.launch {
            loader.load(url).collectLatest { result ->
                result.doOnSuccess {
                    println("Success")
                    imageBitmap = it
                }.doOnFailure {
                    println(it!!)
                }.doOnLoading {
                    println("Loading")
                }
            }
        }
    }

onDispose {
    scope.cancel()
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