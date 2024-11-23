package com.abdulmateen.cmpstartertemplate.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.abdulmateen.cmpstartertemplate.data.datastore.AppDataStorePref
import com.abdulmateen.cmpstartertemplate.network.DataState
import com.abdulmateen.cmpstartertemplate.network.NetWorkResult
import com.abdulmateen.cmpstartertemplate.network.models.LoginResponse
import com.abdulmateen.cmpstartertemplate.network.request.LoginRequestBody
import com.abdulmateen.cmpstartertemplate.network.toResultFlow
import com.abdulmateen.cmpstartertemplate.utils.Constants
import com.abdulmateen.cmpstartertemplate.utils.PrefKeys
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
    private val httpClient: HttpClient,
    private val dataStore: AppDataStorePref
): MainRepository {

    override suspend fun login(body: LoginRequestBody): Flow<DataState<LoginResponse>> = flow {
        try {
            emit(DataState.loading())
            val response = httpClient.post("https://dummyjson.com/auth/login"){
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body<LoginResponse>()

            dataStore.setBoolValue(prefKey = PrefKeys.IS_LOGGED_IN, true)


            emit(DataState.success(response))
        } catch (e: ClientRequestException) { // 4xx errors
            emit(DataState.error(e.response.status.description))
        } catch (e: ServerResponseException) { // 5xx errors
            emit(DataState.error(e.response.status.description))
        } catch (e: IOException) { // Network or I/O errors
            emit(DataState.error(e.message ?: "Something went wrong."))
        } catch (e: Exception) { // Other unhandled exceptions
            emit(DataState.error(e.message ?: "Something went wrong."))
        }
    }
}