package com.abdulmateen.cmpstartertemplate.data.datastore

interface IAppDataStorePref {
    suspend fun setBoolValue(prefKey: String, value: Boolean)
    suspend fun getBoolValue(prefKey: String): Boolean

    suspend fun setStringValue(prefKey: String, value: String)
    suspend fun getStringValue(prefKey: String): String

    suspend fun setIntValue(prefKey: String, value: Int)
    suspend fun getIntValue(prefKey: String): Int
}