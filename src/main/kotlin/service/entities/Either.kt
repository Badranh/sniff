package service.entities

sealed class Either<L, R> {
    class Left<L, R>(val l: L) : Either<L, R>() {
        override fun toString(): String = "Left $l"
    }

    class Right<L, R>(val r: R) : Either<L, R>() {
        override fun toString(): String = "Right $r"
    }

    infix fun <Rp> bind(f: (R) -> (Either<L, Rp>)): Either<L, Rp> {
        return when (this) {
            is Left<L, R> -> Left(this.l)
            is Right<L, R> -> f(this.r)
        }
    }

    infix fun <Rp> seq(e: Either<L, Rp>): Either<L, Rp> = e

    companion object {
        fun <L, R> ret(a: R) = Right<L, R>(a)
        fun <L, R> fail(msg: String): Right<L, R> = throw Exception(msg)
    }
}

