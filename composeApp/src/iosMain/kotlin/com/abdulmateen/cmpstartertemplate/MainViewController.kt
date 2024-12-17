package com.abdulmateen.cmpstartertemplate

import androidx.compose.ui.window.ComposeUIViewController
import com.abdulmateen.cmpstartertemplate.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }