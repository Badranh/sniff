package service.services

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import org.jetbrains.skija.Image
import service.SniffService
import service.entities.Either
import service.retryhandlers.backoffwithratio.retryIO

class KtorService(override var isRetriesEnabled: Boolean) :SniffService {
    private val client = HttpClient(CIO)

    override fun enableRetries() {
        this.isRetriesEnabled = true
    }

    override suspend fun downloadImage(url: String):Either<String,ByteArray> {
        print("asdasdasdadasdad")
       return client.requestOrFail({
           val resp = client.get<ByteArray>(url)
           print("hehe $resp")
           Either.Right(resp)

        },{
           println("failll wlr")
           Either.Left(this.message.toString())
        })
    }

}


fun ByteArray.bitmap():ImageBitmap= Image.makeFromEncoded(this).asImageBitmap()



//seperate Retry and request
suspend fun <T> HttpClient.requestOrFail(
    block: suspend HttpClient.() -> T,
    errorHandler: suspend ResponseException.() -> T
): T = runCatching { retryIO { block() }}
    .getOrElse {
        when (it) {
            is ResponseException -> it.errorHandler()
            else -> throw it
        }
    }