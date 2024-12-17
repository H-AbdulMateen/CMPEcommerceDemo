package com.abdulmateen.cmpstartertemplate

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import com.abdulmateen.cmpstartertemplate.splash.SplashScreen
import com.abdulmateen.cmpstartertemplate.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    AppTheme {
        Navigator(screen = SplashScreen())
    }
}