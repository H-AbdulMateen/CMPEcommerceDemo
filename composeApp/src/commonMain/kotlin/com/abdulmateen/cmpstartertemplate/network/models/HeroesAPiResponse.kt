package com.abdulmateen.cmpstartertemplate.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesAPiResponse(
    @SerialName("response")
    val response: String,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("results-for")
    val resultsFor: String
)