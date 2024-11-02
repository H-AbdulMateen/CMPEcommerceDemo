package com.abdulmateen.cmpstartertemplate.repository

import com.abdulmateen.cmpstartertemplate.network.NetWorkResult
import com.abdulmateen.cmpstartertemplate.network.models.HeroesAPiResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getHeroes(): Flow<NetWorkResult<HeroesAPiResponse>>
}