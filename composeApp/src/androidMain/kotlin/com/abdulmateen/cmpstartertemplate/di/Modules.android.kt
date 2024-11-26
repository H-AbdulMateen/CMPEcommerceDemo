package com.abdulmateen.cmpstartertemplate.di

import com.abdulmateen.cmpstartertemplate.data.datastore.createDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine> { OkHttp.create() }

    single { createDataStore(androidContext()) }
}