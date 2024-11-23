package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

import androidx.lifecycle.ViewModel
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

    val flowEvent = MutableSharedFlow<AuthEvents>()


    private fun login(){
        CoroutineScope(Dispatchers.IO).launch {
            repo.login(
                body = LoginRequestBody(
                    expiresInMins = 45,
                    password = uiState.value.password,
                    username = uiState.value.username
                )
            ).collect{ response ->
                uiEvent(AuthActions.UpdateLoadingStatus(response.loading))
                response.data?.let {
                    _uiState.update {
                        it.copy(
                            dummyData = response.data.toString()
                        )
                    }
                    flowEvent.emit(AuthEvents.LoginSuccess)
                }
                response.error?.let {
                    flowEvent.emit(AuthEvents.PopUpErrorMessage(it))
                }
            }
        }
    }

    fun uiEvent(event: AuthActions){
        when(event){
            is AuthActions.UpdateConfirmPassword -> {
                _uiState.update {
                    it.copy(
                        confirmPassword = event.text
                    )
                }
            }
            is AuthActions.UpdateEmail -> {
                _uiState.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is AuthActions.UpdateFirstName -> {
                _uiState.update {
                    it.copy(
                        firstName = event.text
                    )
                }
            }
            AuthActions.UpdateIsRememberCheck -> {
                _uiState.update {
                    it.copy(
                        isRememberCheck = !uiState.value.isRememberCheck
                    )
                }
            }
            is AuthActions.UpdateLastName -> {
                _uiState.update {
                    it.copy(
                        lastName = event.text
                    )
                }
            }
            is AuthActions.UpdateLoadingStatus -> {
                _uiState.update {
                    it.copy(
                        isLoading = event.isLoading
                    )
                }
            }
            is AuthActions.UpdatePassword -> {
                _uiState.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is AuthActions.UpdatePasswordReg -> {
                _uiState.update {
                    it.copy(
                        passwordReg = event.text
                    )
                }
            }
            is AuthActions.UpdatePhone -> {
                _uiState.update {
                    it.copy(
                        phoneNumber = event.text
                    )
                }
            }
            is AuthActions.UpdateUsername -> {
                _uiState.update {
                    it.copy(
                        username = event.username
                    )
                }
            }
            is AuthActions.UpdateUsernameReg -> {
                _uiState.update {
                    it.copy(
                        usernameReg = event.text
                    )
                }
            }
            is AuthActions.UpdateDateOfBirth -> {
                _uiState.update {
                    it.copy(
                        dateOfBirth = event.text
                    )
                }
            }
            AuthActions.OnLoginClick -> {
                login()
            }
            AuthActions.OnRegisterClick -> {}
            AuthActions.TogglePasswordVisibility -> {
                _uiState.update {
                    it.copy(
                        passwordVisibility = !uiState.value.passwordVisibility
                    )
                }
            }
            AuthActions.ToggleConfirmPasswordVisibility -> {
                _uiState.update {
                    it.copy(
                        confirmPasswordVisibility = !uiState.value.confirmPasswordVisibility
                    )
                }
            }
        }
    }


}