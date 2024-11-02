package com.abdulmateen.cmpstartertemplate

import androidx.compose.ui.window.ComposeUIViewController
import com.abdulmateen.cmpstartertemplate.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }