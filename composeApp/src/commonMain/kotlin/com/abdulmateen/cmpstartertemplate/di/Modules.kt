package com.abdulmateen.cmpstartertemplate.di

import com.abdulmateen.cmpstartertemplate.presentation.screens.auth.AuthViewModel
import com.abdulmateen.cmpstartertemplate.repository.MainRepository
import com.abdulmateen.cmpstartertemplate.repository.MainRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

val provideHttpClientModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
}

val sharedModule = module {
    singleOf(::MainRepositoryImpl).bind<MainRepository>()
    viewModelOf(::AuthViewModel)
}