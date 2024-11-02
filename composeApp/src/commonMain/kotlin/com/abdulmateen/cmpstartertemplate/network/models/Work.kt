package com.abdulmateen.cmpstartertemplate.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Work(
    @SerialName("base")
    val base: String,
    @SerialName("occupation")
    val occupation: String
)