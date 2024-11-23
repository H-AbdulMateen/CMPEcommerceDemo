package com.abdulmateen.cmpstartertemplate.presentation.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cmpecommercedemo.composeapp.generated.resources.Res
import cmpecommercedemo.composeapp.generated.resources.compose_multiplatform
import com.abdulmateen.cmpstartertemplate.presentation.screens.WelcomeScreen
import com.abdulmateen.cmpstartertemplate.presentation.screens.auth.AuthScreen
import com.abdulmateen.cmpstartertemplate.presentation.screens.dashboard.DashboardScreen
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class SplashScreen : Screen {
    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SplashViewModel>()
        val isAuthenticated = viewModel.navigateToDashboard
        SplashScreenBody(isAuthenticated = isAuthenticated)
    }
}

@Composable
fun SplashScreenBody(
    isAuthenticated: Boolean
) {
    val navigator = LocalNavigator.current
    LaunchedEffect(isAuthenticated){
        delay(3000L)
        if (isAuthenticated){
            navigator?.replace(DashboardScreen())
        }else{
            navigator?.replace(WelcomeScreen())
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(painter = painterResource(resource = Res.drawable.compose_multiplatform), contentDescription = "Logo")
    }
}