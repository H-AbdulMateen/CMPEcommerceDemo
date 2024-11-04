package com.abdulmateen.cmpstartertemplate.repository

import com.abdulmateen.cmpstartertemplate.network.NetWorkResult
import com.abdulmateen.cmpstartertemplate.network.models.LoginResponse
import com.abdulmateen.cmpstartertemplate.network.request.LoginRequestBody
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun login(body: LoginRequestBody): Flow<NetWorkResult<LoginResponse>>
}