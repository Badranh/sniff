import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import component.NetworkImage
import platformdirs.PlatformDirsFactory


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
    var text by remember { mutableStateOf("asd") }

    var url by  remember { mutableStateOf(0) }
    var url2 by  remember { mutableStateOf(1) }

    println(PlatformDirsFactory.Factory.build().getCacheDir())
    val imagesArray = arrayListOf("https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png","https://i0.wp.com/www.oakridge.in/wp-content/uploads/2020/02/Sample-jpg-image-500kb.jpg","https://miro.medium.com/max/1838/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg")
    val coroutineScope = rememberCoroutineScope()
    MaterialTheme {
        Row{
            Button(onClick = {
                text = "reload"
                url = (url +1)%3
            }) {
                Text(text)
            }
            NetworkImage(url = imagesArray[url],scope = coroutineScope)
            NetworkImage(url = imagesArray[url],scope = coroutineScope)

        }
    }
}
