package com.abdulmateen.cmpstartertemplate.core.network

import com.abdulmateen.cmpstartertemplate.core.util.Constants

fun constructUrl(url: String): String {
    return when {
        url.contains(Constants.BASE_URL) -> url
        url.startsWith("/") -> Constants.BASE_URL + url.drop(1)
        else -> Constants.BASE_URL + url
    }
}