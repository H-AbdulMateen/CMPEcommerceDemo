package com.abdulmateen.cmpstartertemplate.repository

import com.abdulmateen.cmpstartertemplate.network.Constants
import com.abdulmateen.cmpstartertemplate.network.NetWorkResult
import com.abdulmateen.cmpstartertemplate.network.models.LoginResponse
import com.abdulmateen.cmpstartertemplate.network.request.LoginRequestBody
import com.abdulmateen.cmpstartertemplate.network.toResultFlow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val httpClient: HttpClient
): MainRepository {

    override suspend fun login(body: LoginRequestBody): Flow<NetWorkResult<LoginResponse>> = flow {
        try {
            emit(NetWorkResult.Loading(isLoading = true))
            val response = httpClient.post("https://dummyjson.com/auth/login"){
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body<LoginResponse>()
//            val response = httpClient.post {
//                url {
//                    protocol = URLProtocol.HTTPS
//                    host = Constants.BASE_URL
//                    path("auth/login")
//                    contentType(ContentType.Application.Json)
//                    setBody(body)
//                }
//            }.body<LoginResponse>()
            emit(NetWorkResult.Loading(false))
            emit(NetWorkResult.Success(response))
        } catch (e: ClientRequestException) { // 4xx errors
            emit(NetWorkResult.Loading(false))
            emit(NetWorkResult.Error(_data = null,exception = "Client error: ${e.response.status.description}"))
        } catch (e: ServerResponseException) { // 5xx errors
            emit(NetWorkResult.Loading(false))
            emit(NetWorkResult.Error(_data = null,exception = "Server error: ${e.response.status.description}"))
        } catch (e: IOException) { // Network or I/O errors
            emit(NetWorkResult.Loading(false))
            emit(NetWorkResult.Error(_data = null,exception = "Network error: ${e.message}"))
        } catch (e: Exception) { // Other unhandled exceptions
            emit(NetWorkResult.Loading(false))
            emit(NetWorkResult.Error(_data = null,exception = "Unexpected error: ${e.message}"))
        }
    }
}