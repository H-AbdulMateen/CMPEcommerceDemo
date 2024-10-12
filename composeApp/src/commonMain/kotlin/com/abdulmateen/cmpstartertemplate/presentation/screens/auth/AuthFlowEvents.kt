package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

sealed class AuthFlowEvents {
    data class PopUpErrorMessage(val message: String): AuthFlowEvents()
    data class PopUpSuccessMessage(val message: String): AuthFlowEvents()
}