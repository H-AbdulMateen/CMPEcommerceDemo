package com.abdulmateen.cmpstartertemplate.presentation.screens.auth

data class AuthUIState(
    val isLoading: Boolean = false,
    val username: String = "emilys",
    val password: String = "emilyspass",
    val isRememberCheck: Boolean = false,
    val passwordVisibility: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val usernameReg: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val passwordReg: String = "",
    val confirmPassword: String = "",
    val gender: String = "",
    val genderList: List<String> = listOf("Male", "Female"),
    val confirmPasswordVisibility: Boolean = false,
    val dummyData: String = ""

)
