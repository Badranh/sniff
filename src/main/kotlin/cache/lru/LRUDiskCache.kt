package cache.lru

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import cache.Cache
import org.jetbrains.skija.Image
import platformdirs.PlatformDirsFactory
import service.services.bitmap
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

//TODO:Buy a pizza
//TODO:Testing needed
//TODO:must provide sync and a lot of thingssss
class LRUDiskCache(override val maxByteCount: Int) : Cache {


    private val pathBuilder = PlatformDirsFactory.Factory.build()

    //must be in chunks
    override fun get(key: String): ImageBitmap?{
        val path = File(pathBuilder.getCacheDir())
        if(!path.exists())return null
        val f = File(path, "${key.replace('/', '-').replace(':', '-')}.bin")
        return f.readBytes().bitmap()
    }

    override fun clear() {
        val path =  File(pathBuilder.getCacheDir())
        path.deleteRecursively()
    }

    override fun evictionCount(): Int {
        return 12
    }

    override fun putCount(): Int {
        return 99
    }

    override fun missCount(): Int {
        return 1
    }

    override fun hitCount(): Int {
        return 4
    }

    //must be in chunks
    override fun set(key: String, bitmap: ByteArray) {
        val path =  File(pathBuilder.getCacheDir())
        if(!path.exists()) path.mkdirs()
        val f = File(path,"${key.replace('/','-').replace(':','-')}.bin")
        val os = FileOutputStream(f)
        os.write(bitmap)
        os.close()
    }

    override fun size(): Int {
        val path =  File(pathBuilder.getCacheDir())
        return (path.walkTopDown().filter { it.isFile }.map { it.length() }.sum()).toInt()
    }

    override fun clearKeyUri(uri: String) {
        val path =  File(pathBuilder.getCacheDir()+"/$uri")
        if(path.exists() && path.isFile ) {
            path.delete()
        }
    }

}