package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
class AuthScreen: Screen{
    @Composable
    override fun Content() {
     AuthScreenBody()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthScreenBody(
    viewModel: AuthViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val flowEvents = viewModel.flowEvent
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { Tabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            Surface(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.fillMaxWidth().height(100.dp),
                content = {}
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                TabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Transparent
                ){
                    Tabs.entries.forEachIndexed { index, tab ->
                        Tab(
                            selected = selectedTabIndex.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(tab.ordinal)
                                }
                            },
                            text = {
                                Text(text = tab.name)
                            },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = Black
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        when(pagerState.currentPage){
                            Tabs.Login.ordinal -> LoginContent()
                            Tabs.Register.ordinal -> RegisterContent()
                        }
                    }
                }
            }

            
        }
    }
}

@Composable
fun LoginContent(modifier: Modifier = Modifier) {
    val state = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(state)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Sign In",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Welcome back! Don't have an account?")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Sign Up",
            style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary)
        )

    }
}
@Composable
fun RegisterContent(modifier: Modifier = Modifier) {

}

enum class Tabs {
    Login,
    Register
}

@Preview
@Composable
fun LoginScreenPreview() {
    AuthScreenBody()
}