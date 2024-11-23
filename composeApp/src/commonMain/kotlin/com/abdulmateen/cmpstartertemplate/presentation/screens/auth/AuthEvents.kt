package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

sealed class AuthEvents {
    data class PopUpErrorMessage(val message: String): AuthEvents()
    data class PopUpSuccessMessage(val message: String): AuthEvents()
    data object LoginSuccess: AuthEvents()
}