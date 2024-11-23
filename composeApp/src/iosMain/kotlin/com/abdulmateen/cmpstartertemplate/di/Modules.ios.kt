package com.abdulmateen.cmpstartertemplate.di

import com.abdulmateen.cmpstartertemplate.data.datastore.createDataStore
import org.koin.dsl.module

actual val platformModule = module {
    single { createDataStore() }
}