package platformdirs

import platformdirs.implementation.MacOS
import platformdirs.implementation.Unix
import platformdirs.implementation.Windows

class PlatformDirsFactory {
    object Factory {
        private lateinit var instance:PlatformDirs
        fun build():PlatformDirs{
            val os = System.getProperty("os.name")
            println(os)
            if(::instance.isInitialized) return instance
            instance = when {
                os.toLowerCase().contains("os x") -> MacOS()
                os.toLowerCase().contains("windows") -> Windows()
                else -> Unix()
            }
            return instance
        }
    }
}