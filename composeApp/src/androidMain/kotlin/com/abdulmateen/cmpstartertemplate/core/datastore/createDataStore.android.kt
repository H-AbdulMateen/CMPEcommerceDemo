package com.abdulmateen.cmpstartertemplate.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.cmpstartertemplate.core.data.datastore.dataStoreFileName

fun createDataStore(context: Context): DataStore<Preferences> =
    com.abdulmateen.cmpstartertemplate.core.data.datastore.createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )