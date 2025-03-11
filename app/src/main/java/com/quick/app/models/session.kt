package com.quick.app.models

data class Session(
    val userId: String,
    val session: String,
    val chatToken: String,
    val user: User,
)