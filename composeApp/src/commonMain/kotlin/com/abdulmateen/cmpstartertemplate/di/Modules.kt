package com.abdulmateen.cmpstartertemplate.di

import com.abdulmateen.cmpstartertemplate.data.datastore.AppDataStorePref
import com.abdulmateen.cmpstartertemplate.data.datastore.IAppDataStorePref
import com.abdulmateen.cmpstartertemplate.presentation.screens.auth.AuthViewModel
import com.abdulmateen.cmpstartertemplate.presentation.screens.splash.SplashViewModel
import com.abdulmateen.cmpstartertemplate.repository.MainRepository
import com.abdulmateen.cmpstartertemplate.repository.MainRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

expect val platformModule: Module

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
    singleOf(::AppDataStorePref).bind<IAppDataStorePref>()
    viewModelOf(::AuthViewModel)
    viewModelOf(::SplashViewModel)
}