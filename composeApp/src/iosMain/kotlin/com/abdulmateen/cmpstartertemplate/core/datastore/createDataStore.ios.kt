package com.abdulmateen.cmpstartertemplate.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.cmpstartertemplate.core.data.datastore.dataStoreFileName
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

// shared/src/iosMain/kotlin/createDataStore.kt

@OptIn(ExperimentalForeignApi::class)
fun createDataStore(): DataStore<Preferences> =
    com.abdulmateen.cmpstartertemplate.core.data.datastore.createDataStore(
        producePath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }
    )