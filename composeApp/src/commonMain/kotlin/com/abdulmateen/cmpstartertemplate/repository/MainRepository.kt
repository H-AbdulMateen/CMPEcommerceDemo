package com.abdulmateen.cmpstartertemplate.repository

import com.abdulmateen.cmpstartertemplate.auth.data.network.DataState
import com.abdulmateen.cmpstartertemplate.auth.data.dto.LoginResponseDto
import com.abdulmateen.cmpstartertemplate.auth.data.dto.request.LoginRequestBody
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun login(body: LoginRequestBody): Flow<DataState<LoginResponseDto>>
}