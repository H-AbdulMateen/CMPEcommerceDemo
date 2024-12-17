package com.abdulmateen.cmpstartertemplate.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cmpecommercedemo.composeapp.generated.resources.Res
import cmpecommercedemo.composeapp.generated.resources.ic_logout
import cmpecommercedemo.composeapp.generated.resources.ic_settings
import com.abdulmateen.cmpstartertemplate.core.data.datastore.AppDataStorePref
import com.abdulmateen.cmpstartertemplate.auth.presentation.AuthScreen
import com.abdulmateen.cmpstartertemplate.core.utils.PrefKeys
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

object SettingsScreen : Tab {
    @Composable
    override fun Content() {
        SettingsScreenBody()
    }


    override val options: TabOptions
        @Composable
        get() {
            val title = "Settings"
            val icon = painterResource(Res.drawable.ic_settings)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SettingsScreenBody(
        dataStore: AppDataStorePref = koinInject()
    ) {
        val scope = rememberCoroutineScope()
        val navigator = LocalNavigator.current
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Settings Screen")
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ) {
                ListItem(
                    leadingContent = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_logout),
                            contentDescription = "Logout"
                        )
                    },
                    headlineContent = {
                        Text(text = "Logout")
                    },
                    modifier = Modifier.clickable(
                        onClick = {
                            scope.launch {
                                dataStore.setBoolValue(
                                    prefKey = PrefKeys.IS_LOGGED_IN,
                                    value = false
                                )
                                navigator?.parent?.replace(AuthScreen())

                            }
                        }
                    )
                )
            }
        }
    }

}