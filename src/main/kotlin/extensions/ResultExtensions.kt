package extensions

import data.Result

inline fun <reified T> Result<T>.doOnFailure(block: (error: String?) -> Unit) : Result<T>{
    if (this is Result.Failure) {
        block(message)
    }
    return this
}

inline  fun <reified T> Result<T>.doOnSuccess(block: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) {
        block(value)
    }
    return this
}

inline  fun <reified T> Result<T>.doOnLoading(block: (value: T?) -> Unit): Result<T> {
    if (this is Result.Loading) {
        block(null)
    }
    return this
}