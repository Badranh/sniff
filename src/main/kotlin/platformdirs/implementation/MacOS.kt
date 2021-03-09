package platformdirs.implementation

import platformdirs.PlatformDirs

class MacOS : PlatformDirs {
    override fun getCacheDir(): String {
       return homeDir()+"/Library/Caches/Sniff/1.0.0"
    }
}