package extensions

import java.io.File
fun File.chunkedSequence(chunk: Int): Sequence<ByteArray> {
    val input = this.inputStream().buffered()
    val buffer = ByteArray(chunk)
    return generateSequence {
        val red = input.read(buffer)
        if (red >= 0){
            buffer.copyOf(red)
        }
        else {
            input.close()
            null
        }
    }
}