import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import cache.factory.CacheBuilder
import cache.factory.CachingStrategy
import cache.factory.withStrategy
import component.NetworkImage
import kotlinx.coroutines.launch
import loader.BaseLoader
import loader.BasicLoader
import service.services.KtorService
import sniff.Sniff



//TODO:Singleton instance of Sniff from factory
//TODO:boolean to enable fade upon loading
//TODO:Place holder when loading
//TODO:Optimize in-mem caching
//TODO:Create Disk caching
//TODO:create factories for each service
//TODO:enhance fallbacks and retries of network calls, apply them in their correct way
//TODO:modularize the project into different modules
//TODO:let user Apply image color filters

fun main() = Window {
    var text by remember { mutableStateOf("Reloasdfd") }
    val coroutineScope = rememberCoroutineScope()
    MaterialTheme {

        NetworkImage(url = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png",scope = coroutineScope)
        Button(onClick = {
            text = "Reload"
        }) {
            Text(text)
        }
    }
}
