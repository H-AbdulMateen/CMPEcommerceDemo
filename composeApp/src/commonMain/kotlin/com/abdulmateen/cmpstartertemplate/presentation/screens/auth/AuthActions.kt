package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

sealed class AuthActions {
    data class UpdateLoadingStatus(val isLoading: Boolean): AuthActions()
    data class UpdateUsername(val username: String): AuthActions()
    data class UpdatePassword(val password: String): AuthActions()
    data object TogglePasswordVisibility: AuthActions()
    data object UpdateIsRememberCheck: AuthActions()
    data object OnLoginClick: AuthActions()
    data class UpdateFirstName(val text: String): AuthActions()
    data class UpdateLastName(val text: String): AuthActions()
    data class UpdateUsernameReg(val text: String): AuthActions()
    data class UpdatePasswordReg(val text: String): AuthActions()
    data class UpdateEmail(val email: String): AuthActions()
    data class UpdatePhone(val text: String): AuthActions()
    data class UpdateConfirmPassword(val text: String): AuthActions()
    data class UpdateDateOfBirth(val text: String): AuthActions()
    data object OnRegisterClick: AuthActions()
    data object ToggleConfirmPasswordVisibility: AuthActions()
}