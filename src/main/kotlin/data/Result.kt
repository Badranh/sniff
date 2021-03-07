package data

public sealed class Result<out T> {
    data class Success<out R>(val value: R): Result<R>()
    data class Failure(val message: String?): Result<Nothing>()
    //place holder
    data class Loading(val progress:Int = 0): Result<Nothing>()

}