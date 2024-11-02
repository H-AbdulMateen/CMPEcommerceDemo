package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

sealed class AuthUIEvents {
    data class UpdateLoadingStatus(val isLoading: Boolean): AuthUIEvents()
    data class UpdateUsername(val username: String): AuthUIEvents()
    data class UpdatePassword(val password: String): AuthUIEvents()
    data object TogglePasswordVisibility: AuthUIEvents()
    data object UpdateIsRememberCheck: AuthUIEvents()
    data object OnLoginClick: AuthUIEvents()
    data class UpdateFirstName(val text: String): AuthUIEvents()
    data class UpdateLastName(val text: String): AuthUIEvents()
    data class UpdateUsernameReg(val text: String): AuthUIEvents()
    data class UpdatePasswordReg(val text: String): AuthUIEvents()
    data class UpdateEmail(val email: String): AuthUIEvents()
    data class UpdatePhone(val text: String): AuthUIEvents()
    data class UpdateConfirmPassword(val text: String): AuthUIEvents()
    data class UpdateDateOfBirth(val text: String): AuthUIEvents()
    data object OnRegisterClick: AuthUIEvents()
    data object ToggleConfirmPasswordVisibility: AuthUIEvents()
}