package com.abdulmateen.cmpstartertemplate.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class AppDataStorePref(
    private val dataStore: DataStore<Preferences>
): IAppDataStorePref {
    override suspend fun setBoolValue(prefKey: String, value: Boolean) {
        dataStore.edit {pref ->
            pref[booleanPreferencesKey(prefKey)] = value
        }
    }

    override suspend fun getBoolValue(prefKey: String): Boolean {
        return dataStore.data.first()[booleanPreferencesKey(prefKey)] ?: false
    }

    override suspend fun setStringValue(prefKey: String, value: String) {
        dataStore.edit {pref ->
            pref[stringPreferencesKey(prefKey)] = value
        }
    }

    override suspend fun getStringValue(prefKey: String): String {
        return dataStore.data.first()[stringPreferencesKey(prefKey)] ?: ""
    }

    override suspend fun setIntValue(prefKey: String, value: Int) {
        dataStore.edit {pref ->
            pref[intPreferencesKey(prefKey)] = value
        }
    }

    override suspend fun getIntValue(prefKey: String): Int {
        return dataStore.data.first()[intPreferencesKey(prefKey)] ?: 0
    }

}