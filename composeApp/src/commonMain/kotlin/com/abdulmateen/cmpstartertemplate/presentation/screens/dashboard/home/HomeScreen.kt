package com.abdulmateen.cmpstartertemplate.presentation.screens.dashboard.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cmpecommercedemo.composeapp.generated.resources.Res
import cmpecommercedemo.composeapp.generated.resources.ic_home
import org.jetbrains.compose.resources.painterResource

object HomeScreen: Tab {
    @Composable
    override fun Content() {
        HomeScreenBody()
    }

    override val options: TabOptions
        @Composable
        get(){
            val title = "Home"
            val icon = painterResource(Res.drawable.ic_home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenBody() {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text(text = "Home") }
        ) }
    ) { innnerPading ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(innnerPading)
            .background(color = Green),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Home Screen", color = White)
        }
    }
}