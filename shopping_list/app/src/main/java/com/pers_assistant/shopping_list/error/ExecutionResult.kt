package com.pers_assistant.shopping_list.error


sealed class ExecutionResult<out F, out T> {
    data class Fail<out F>(val failure: F) : ExecutionResult<F, Nothing>()
    data class Success<out T>(val success: T) : ExecutionResult<Nothing, T>()

    fun either(functionError: ((F) -> Any)? = null, functionSuccess: ((T) -> Any)? = null): Any? =
        when (this) {
            is Fail -> functionError?.let { it(failure) }
            is Success -> functionSuccess?.let { it(success) }
        }

    inline fun <L, R> fold(
        functionLeft: ((F) -> L),
        functionRight: ((T) -> R)
    ): ExecutionResult<L, R> {
        return when (this) {
            is Fail -> Fail(functionLeft(failure))
            is Success -> Success(functionRight(success))
        }
    }

    inline fun <L, R> foldError(
        functionLeft: ((F) -> Unit),
        functionRight: ((T) -> R)
    ) {
        when (this) {
            is Fail -> functionLeft(failure)
            is Success -> functionRight(success)
        }
    }

    fun takeFailOrNull() = if (this is Fail) this else null
    fun takeSuccessOrNull() = if (this is Success) this else null

    fun isFail() = this is Fail
    fun isSuccess() = this is Success

}

inline fun <reified F, S> ExecutionResult<F, S>.onFail(func: (F) -> Unit): ExecutionResult<F, S> {
    if (this is ExecutionResult.Fail) {
        func.invoke(this.failure)
    }
    return this
}

inline fun <reified F, reified S> ExecutionResult<F, S>.onSuccess(func: (S) -> Unit): ExecutionResult<F, S> {
    if (this is ExecutionResult.Success) {
        func.invoke(this.success)
    }
    return this
}

fun <T> T.toSuccess(): ExecutionResult.Success<T> {
    return ExecutionResult.Success(this)
}