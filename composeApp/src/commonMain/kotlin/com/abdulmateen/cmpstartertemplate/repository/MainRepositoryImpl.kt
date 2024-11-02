package com.abdulmateen.cmpstartertemplate.repository

import com.abdulmateen.cmpstartertemplate.network.Constants
import com.abdulmateen.cmpstartertemplate.network.NetWorkResult
import com.abdulmateen.cmpstartertemplate.network.models.HeroesAPiResponse
import com.abdulmateen.cmpstartertemplate.network.toResultFlow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val httpClient: HttpClient
): MainRepository {

    override suspend fun getHeroes(): Flow<NetWorkResult<HeroesAPiResponse>> = flow {
        try {
            val response = httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constants.BASE_URL
                    path("TOKEN_KEY/search/batman")
                }
            }.body<HeroesAPiResponse>()
            emit(NetWorkResult.Success(response))
        } catch (e: ClientRequestException) { // 4xx errors
            emit(NetWorkResult.Error(_data = null,exception = "Client error: ${e.response.status.description}"))
        } catch (e: ServerResponseException) { // 5xx errors
            emit(NetWorkResult.Error(_data = null,exception = "Server error: ${e.response.status.description}"))
        } catch (e: IOException) { // Network or I/O errors
            emit(NetWorkResult.Error(_data = null,exception = "Network error: ${e.message}"))
        } catch (e: Exception) { // Other unhandled exceptions
            emit(NetWorkResult.Error(_data = null,exception = "Unexpected error: ${e.message}"))
        }
    }
}