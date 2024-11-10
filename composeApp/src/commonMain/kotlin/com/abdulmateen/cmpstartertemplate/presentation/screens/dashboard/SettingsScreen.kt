package com.abdulmateen.cmpstartertemplate.presentation.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cmpecommercedemo.composeapp.generated.resources.Res
import cmpecommercedemo.composeapp.generated.resources.ic_home
import cmpecommercedemo.composeapp.generated.resources.ic_settings
import org.jetbrains.compose.resources.painterResource

object SettingsScreen : Tab {
    @Composable
    override fun Content() {
        SettingsScreenBody()
    }


    override val options: TabOptions
        @Composable
        get(){
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
    fun SettingsScreenBody() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Settings Screen")
                    }
                )
            }
        ) {innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ) {
                ListItem(
                    headlineContent = {
                        Text(text = "Logout")
                    }
                )
            }
        }
    }

}