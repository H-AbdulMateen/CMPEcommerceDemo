package com.abdulmateen.cmpstartertemplate.di

import com.abdulmateen.cmpstartertemplate.data.datastore.createDataStore
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine> { Darwin.create() }
    single { createDataStore() }
}