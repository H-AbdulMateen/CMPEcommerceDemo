package com.abdulmateen.cmpstartertemplate.auth.data.dto.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestBody(
    @SerialName("expiresInMins")
    val expiresInMins: Int,
    @SerialName("password")
    val password: String,
    @SerialName("username")
    val username: String
)