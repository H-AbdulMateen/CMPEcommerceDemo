package com.abdulmateen.cmpstartertemplate.auth.presentation

sealed class AuthEvents {
    data class PopUpErrorMessage(val message: String): AuthEvents()
    data class PopUpSuccessMessage(val message: String): AuthEvents()
    data object LoginSuccess: AuthEvents()
}