package com.abdulmateen.cmpstartertemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform