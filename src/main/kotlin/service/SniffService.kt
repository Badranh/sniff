package service

import service.entities.Either

interface SniffService {
    var isRetriesEnabled:Boolean
    fun enableRetries()
    suspend fun downloadImage(url:String):Either<String,ByteArray>
}