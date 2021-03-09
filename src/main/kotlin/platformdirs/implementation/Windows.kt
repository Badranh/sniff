package platformdirs.implementation

import platformdirs.PlatformDirs

class Windows : PlatformDirs{
    //TODO:Implement and test
    override fun getCacheDir(): String {
        return "Windows"
    }
}