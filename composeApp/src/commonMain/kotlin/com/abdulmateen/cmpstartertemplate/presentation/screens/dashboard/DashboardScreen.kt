package com.abdulmateen.cmpstartertemplate.presentation.screens.dashboard

import SlideTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.ScreenTransition
import com.abdulmateen.cmpstartertemplate.presentation.screens.dashboard.home.HomeScreen
import com.abdulmateen.cmpstartertemplate.presentation.screens.dashboard.profile.ProfileScreen

@OptIn(ExperimentalVoyagerApi::class)
class DashboardScreen: Screen, ScreenTransition by SlideTransition() {
    @Composable
    override fun Content() {
        DashboardScreenBody()
    }
}

@Composable
fun DashboardScreenBody() {
    TabNavigator(HomeScreen){
        Scaffold(
            content = {
                CurrentTab()
            },
            bottomBar = {
                NavigationBar {
                    TabItem(HomeScreen)
                    TabItem(ProfileScreen)
                    TabItem(SettingsScreen)
                }
            }
        )
    }

}

@Composable
fun RowScope.TabItem(tab: Tab) {
    val navigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = navigator.current == tab,
        onClick = { navigator.current = tab },
        label = {
            Text(
                text = tab.options.title
            )
        },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                )
            }
        }
    )
}