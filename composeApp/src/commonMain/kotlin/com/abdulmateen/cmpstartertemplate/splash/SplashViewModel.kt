package com.abdulmateen.cmpstartertemplate.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulmateen.cmpstartertemplate.core.data.datastore.AppDataStorePref
import com.abdulmateen.cmpstartertemplate.core.utils.PrefKeys
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dataStore: AppDataStorePref
): ViewModel() {
    var navigateToDashboard by mutableStateOf(false)

    init {
        viewModelScope.launch {
            observeDataStore()
        }
    }

    private suspend fun observeDataStore() {
        val isLoggedIn = dataStore.getBoolValue(PrefKeys.IS_LOGGED_IN)
        navigateToDashboard = isLoggedIn
    }
}