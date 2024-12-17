package com.abdulmateen.cmpstartertemplate.dashboard.profile

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
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cmpecommercedemo.composeapp.generated.resources.Res
import cmpecommercedemo.composeapp.generated.resources.ic_home
import cmpecommercedemo.composeapp.generated.resources.ic_person
import org.jetbrains.compose.resources.painterResource

object ProfileScreen: Tab {
    @Composable
    override fun Content() {
        ProfileScreenBody()
    }


    override val options: TabOptions
        @Composable
        get(){
            val title = "Profile"
            val icon = painterResource(Res.drawable.ic_person)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenBody() {
    Scaffold(
        topBar = { TopAppBar(
            title = { Text(text = "Profile Screen") }
        ) }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .background(color = Red),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Profile Screen", color = White)
        }
    }
}