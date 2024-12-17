package com.abdulmateen.cmpstartertemplate.auth.data.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> toResultFlow(call: suspend () -> DataState<T?>): Flow<DataState<T>> {
    return flow {
        emit(DataState.loading())
        val c = call.invoke()
        c.let { response ->
            try {
                println("response${response.data}")
                response.data?.let {
                    emit(DataState.success(response.data))
                }
            } catch (e: Exception) {
                emit(DataState.error(e.message ?: "Something went wrong."))
            }
        }
    }
}
sealed class NetWorkResult<out T>(val status: ApiStatus, val data: T?, val message: String?) {
    data class Success<out T>(val _data: T?) : NetWorkResult<T>(status = ApiStatus.SUCCESS, data = _data, message = null)
    data class Error<out T>(val _data: T?, val exception: String) : NetWorkResult<T>(status = ApiStatus.ERROR, data = _data, message = exception)
    data class Loading<out T>(val isLoading: Boolean) : NetWorkResult<T>(status = ApiStatus.LOADING, data = null, message = null)
}


data class DataState<out T>(
    val data: T? = null,
    val error: String? = null,
    val loading: Boolean = false,
){
    companion object{

        fun <T> success(
            data: T
        ): DataState<T> {
            return DataState(
                data = data,
            )
        }

        fun <T> error(
            message: String,
        ): DataState<T> {
            return DataState(
                error = message
            )
        }

        fun <T> loading(): DataState<T> = DataState(loading = true)
    }
}

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING,
}