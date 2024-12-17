package com.abdulmateen.cmpstartertemplate.core.di

import com.abdulmateen.cmpstartertemplate.core.data.network.HttpClientFactory
import com.abdulmateen.cmpstartertemplate.core.data.datastore.AppDataStorePref
import com.abdulmateen.cmpstartertemplate.core.data.datastore.IAppDataStorePref
import com.abdulmateen.cmpstartertemplate.auth.presentation.AuthViewModel
import com.abdulmateen.cmpstartertemplate.splash.SplashViewModel
import com.abdulmateen.cmpstartertemplate.repository.MainRepository
import com.abdulmateen.cmpstartertemplate.repository.MainRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

//val provideHttpClientModule = module {
//    single {
//        HttpClient {
//            install(ContentNegotiation) {
//                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
//            }
//        }
//    }
//}

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::MainRepositoryImpl).bind<MainRepository>()
    singleOf(::AppDataStorePref).bind<IAppDataStorePref>()

    viewModelOf(::AuthViewModel)
    viewModelOf(::SplashViewModel)
}