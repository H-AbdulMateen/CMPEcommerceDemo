package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

import androidx.lifecycle.ViewModel
import com.abdulmateen.cmpstartertemplate.network.ApiStatus
import com.abdulmateen.cmpstartertemplate.network.request.LoginRequestBody
import com.abdulmateen.cmpstartertemplate.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: MainRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState: StateFlow<AuthUIState> = _uiState

    val flowEvent = MutableSharedFlow<AuthFlowEvents>()


    private fun login(){
        CoroutineScope(Dispatchers.IO).launch {
            repo.login(
                body = LoginRequestBody(
                    expiresInMins = 45,
                    password = uiState.value.password,
                    username = uiState.value.username
                )
            ).collect{ response ->
                uiEvent(AuthUIEvents.UpdateLoadingStatus(response.loading))
                response.data?.let {
                    _uiState.update {
                        it.copy(
                            dummyData = response.data.toString()
                        )
                    }
                    flowEvent.emit(AuthFlowEvents.LoginSuccess)
                }
                response.error?.let {
                    flowEvent.emit(AuthFlowEvents.PopUpErrorMessage(it))
                }
            }
        }
    }

    fun uiEvent(event: AuthUIEvents){
        when(event){
            is AuthUIEvents.UpdateConfirmPassword -> {
                _uiState.update {
                    it.copy(
                        confirmPassword = event.text
                    )
                }
            }
            is AuthUIEvents.UpdateEmail -> {
                _uiState.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is AuthUIEvents.UpdateFirstName -> {
                _uiState.update {
                    it.copy(
                        firstName = event.text
                    )
                }
            }
            AuthUIEvents.UpdateIsRememberCheck -> {
                _uiState.update {
                    it.copy(
                        isRememberCheck = !uiState.value.isRememberCheck
                    )
                }
            }
            is AuthUIEvents.UpdateLastName -> {
                _uiState.update {
                    it.copy(
                        lastName = event.text
                    )
                }
            }
            is AuthUIEvents.UpdateLoadingStatus -> {
                _uiState.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
            is AuthUIEvents.UpdatePassword -> {
                _uiState.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is AuthUIEvents.UpdatePasswordReg -> {
                _uiState.update {
                    it.copy(
                        passwordReg = event.text
                    )
                }
            }
            is AuthUIEvents.UpdatePhone -> {
                _uiState.update {
                    it.copy(
                        phoneNumber = event.text
                    )
                }
            }
            is AuthUIEvents.UpdateUsername -> {
                _uiState.update {
                    it.copy(
                        username = event.username
                    )
                }
            }
            is AuthUIEvents.UpdateUsernameReg -> {
                _uiState.update {
                    it.copy(
                        usernameReg = event.text
                    )
                }
            }
            is AuthUIEvents.UpdateDateOfBirth -> {
                _uiState.update {
                    it.copy(
                        dateOfBirth = event.text
                    )
                }
            }
            AuthUIEvents.OnLoginClick -> {
                login()
            }
            AuthUIEvents.OnRegisterClick -> {}
            AuthUIEvents.TogglePasswordVisibility -> {
                _uiState.update {
                    it.copy(
                        passwordVisibility = !uiState.value.passwordVisibility
                    )
                }
            }
            AuthUIEvents.ToggleConfirmPasswordVisibility -> {
                _uiState.update {
                    it.copy(
                        confirmPasswordVisibility = !uiState.value.confirmPasswordVisibility
                    )
                }
            }
        }
    }


}