package com.abdulmateen.cmpstartertemplate.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cmpecommercedemo.composeapp.generated.resources.Res
import cmpecommercedemo.composeapp.generated.resources.all_your_shopping_in_one_app
import cmpecommercedemo.composeapp.generated.resources.undraw_successful_purchase_re_mpig
import cmpecommercedemo.composeapp.generated.resources.welcome_screen_note
import com.abdulmateen.cmpstartertemplate.presentation.components.ButtonSimple
import com.abdulmateen.cmpstartertemplate.presentation.screens.auth.AuthScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

class WelcomeScreen: Screen {
    @Composable
    override fun Content() {
        WelcomeScreenBody()
    }
}

@Composable
fun WelcomeScreenBody() {
    val navigator = LocalNavigator.currentOrThrow
    Scaffold{

        Column(modifier = Modifier.fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(resource = Res.drawable.undraw_successful_purchase_re_mpig), contentDescription = "Logo")
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = stringResource(Res.string.all_your_shopping_in_one_app),
                style = MaterialTheme.typography.displaySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(Res.string.welcome_screen_note))
            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                ButtonSimple(
                    onClick = { navigator.push(AuthScreen()) },
                    label = "Sign In",
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )

                ButtonSimple(
                    onClick = { /*TODO*/ },
                    label = "Sign Up",
                    modifier = Modifier.weight(1f).padding(start = 8.dp),
                    backgroundColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreenBody()
}