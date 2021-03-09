package platformdirs

interface PlatformDirs {

    fun homeDir():String{
        return  System.getProperty("user.home")
    }

    fun getCacheDir():String
}