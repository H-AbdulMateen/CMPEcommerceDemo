package com.abdulmateen.cmpstartertemplate.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(sharedModule, provideHttpClientModule)
    }
}