package com.abdulmateen.cmpstartertemplate.auth.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("email")
    val email: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("username")
    val username: String
)