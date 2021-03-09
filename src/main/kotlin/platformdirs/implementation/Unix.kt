package platformdirs.implementation

import platformdirs.PlatformDirs

class Unix:PlatformDirs {

    //TODO:Implement and test
    override fun getCacheDir(): String {
        return "Unix"
    }
}