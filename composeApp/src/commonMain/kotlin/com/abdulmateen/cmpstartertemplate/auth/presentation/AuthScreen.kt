package com.abdulmateen.cmpstartertemplate.auth.presentation

import SlideTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.ScreenTransition
import com.abdulmateen.cmpstartertemplate.core.presentation.components.ButtonSimple
import com.abdulmateen.cmpstartertemplate.core.presentation.components.DatePickerOutlinedTextField
import com.abdulmateen.cmpstartertemplate.core.presentation.components.PasswordOutlinedTextField
import com.abdulmateen.cmpstartertemplate.core.presentation.components.SimpleOutlinedTextField
import com.abdulmateen.cmpstartertemplate.auth.presentation.components.WheelDateTimePickerDialog
import com.abdulmateen.cmpstartertemplate.dashboard.DashboardScreen
import network.chaintech.cmpimagepickncrop.CMPImagePickNCropDialog
import network.chaintech.cmpimagepickncrop.imagecropper.rememberImageCropper
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalVoyagerApi::class)
class AuthScreen : Screen, ScreenTransition by SlideTransition() {
    @Composable
    override fun Content() {
        AuthScreenBody()
    }
}


@OptIn(ExperimentalFoundationApi::class, KoinExperimentalAPI::class)
@Composable
fun AuthScreenBody(
    viewModel: AuthViewModel = koinViewModel<AuthViewModel>()
) {
    val uiState = viewModel.uiState.collectAsState().value
    val flowEvents = viewModel.flowEvent
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { Tabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val snackbarHostState = remember { SnackbarHostState() }
    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(Unit){
        flowEvents.collect{event ->
            when(event){
                is AuthEvents.PopUpErrorMessage -> {
                    scope.launch {
                        val result = snackbarHostState
                            .showSnackbar(
                                message = event.message,
                                actionLabel = "Cancel",
                                // Defaults to SnackbarDuration.Short
                                duration = SnackbarDuration.Short
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                /* Handle snackbar action performed */

                            }

                            SnackbarResult.Dismissed -> {
                                /* Handle snackbar dismissed */
                            }
                        }
                    }
                }
                is AuthEvents.PopUpSuccessMessage -> {
                }
                is AuthEvents.LoginSuccess -> {
                    navigator.popAll()
                    navigator.replace(DashboardScreen())
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
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
                ) {
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
                        when (pagerState.currentPage) {
                            Tabs.Login.ordinal -> LoginContent(
                                uiState = uiState,
                                uiEvent = viewModel::uiEvent
                            )

                            Tabs.Register.ordinal -> RegisterContent(
                                uiState = uiState,
                                uiEvent = viewModel::uiEvent
                            )
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: AuthUIState,
    uiEvent: (AuthActions) -> Unit
) {
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
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SimpleOutlinedTextField(
            value = uiState.username,
            onValueChange = { uiEvent(AuthActions.UpdateUsername(it)) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Username"
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordOutlinedTextField(
            value = uiState.password,
            onValueChange = {
                uiEvent(AuthActions.UpdatePassword(it))
            },
            isVisible = uiState.passwordVisibility,
            onVisibilityClick = {
                uiEvent(AuthActions.TogglePasswordVisibility)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Password"
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonSimple(
            label = "Login",
            onClick = { uiEvent(AuthActions.OnLoginClick) },
            modifier = Modifier.fillMaxWidth()
        )
        if (uiState.isLoading){
            CircularProgressIndicator()
        }
        Text(
            text = uiState.dummyData
        )
    }
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    uiState: AuthUIState,
    uiEvent: (AuthActions) -> Unit
) {
    val state = rememberScrollState()
    var isCalendarVisible by remember { mutableStateOf(false) }
    val imageCropper = rememberImageCropper()
    var selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var openImagePicker by remember { mutableStateOf(value = false) }

    CMPImagePickNCropDialog(
        imageCropper = imageCropper,
        openImagePicker = openImagePicker,
        imagePickerDialogHandler = {
            openImagePicker = it
        },
        selectedImageCallback = {
            selectedImage = it
        })
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(state)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "SignUp",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Create your account")
        selectedImage?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                modifier = Modifier
                    .clip(
                        shape = CircleShape
                    )
                    .size(100.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable(
                    onClick = {
                        openImagePicker = true
                    }
                ),
                contentScale = ContentScale.Crop
            )
        }
        if (selectedImage == null)
            Box(
                modifier = Modifier
                    .clip(
                        shape = CircleShape
                    )
                    .align(alignment = Alignment.CenterHorizontally)
                    .background(color = LightGray)
                    .size(100.dp).clickable(
                    onClick = {
                        openImagePicker = true
                    }
                )
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            SimpleOutlinedTextField(
                value = uiState.firstName,
                onValueChange = { uiEvent(AuthActions.UpdateFirstName(it)) },
                modifier = Modifier.weight(.1f).padding(end = 4.dp),
                placeholder = "First Name"
            )
            SimpleOutlinedTextField(
                value = uiState.lastName,
                onValueChange = { uiEvent(AuthActions.UpdateLastName(it)) },
                modifier = Modifier.weight(.1f),
                placeholder = "Last Name"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        SimpleOutlinedTextField(
            value = uiState.username,
            onValueChange = { uiEvent(AuthActions.UpdateUsername(it)) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Username"
        )
        Spacer(modifier = Modifier.height(8.dp))
        SimpleOutlinedTextField(
            value = uiState.email,
            onValueChange = { uiEvent(AuthActions.UpdateEmail(it)) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "E-Mail"
        )
        Spacer(modifier = Modifier.height(8.dp))
        DatePickerOutlinedTextField(
            value = uiState.dateOfBirth,
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Date Of Birth",
            onClick = {
                isCalendarVisible = true
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordOutlinedTextField(
            value = uiState.passwordReg,
            onValueChange = {
                uiEvent(AuthActions.UpdatePasswordReg(it))
            },
            isVisible = uiState.passwordVisibility,
            onVisibilityClick = {
                uiEvent(AuthActions.TogglePasswordVisibility)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Password"
        )
        Spacer(modifier = Modifier.height(8.dp))
        PasswordOutlinedTextField(
            value = uiState.confirmPassword,
            onValueChange = {
                uiEvent(AuthActions.UpdateConfirmPassword(it))
            },
            isVisible = uiState.confirmPasswordVisibility,
            onVisibilityClick = {
                uiEvent(AuthActions.ToggleConfirmPasswordVisibility)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Confirm Password"
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonSimple(
            label = "Register",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
    if (isCalendarVisible) {
        WheelDateTimePickerDialog(
            showDatePicker = isCalendarVisible,
            toggleDatePicker = { isCalendarVisible = it },
            onDateSelection = { uiEvent(AuthActions.UpdateDateOfBirth(it)) }
        )
    }
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