package com.quick.app.models


data class LoginParams(
    val phone: String? = null,
    val email: String? = null,
    val password: String,
)

data class Session(
    val userId: String,
    val session: String,
    val chatToken: String,
    val user: User,
)

data class User(
    val id: String? = null,
    val nickname: String? = null,
    val icon: String? = null,
    val detail: String? = null,
    val gender: String = "0",
    val birthday: String? = null,
    val bg: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val password: String? = null,
    val qqNickname: String? = null,
    val wechatNickname: String? = null,
    val code: String? = null,
    val province: String? = null,
    val provinceCode: String? = null,
    val city: String? = null,
    val cityCode: String? = null,
    val area: String? = null,
    val areaCode: String? = null,
    val followingsCount: Long = 0,
    val followersCount: Long = 0,
    val following: String? = null,
    val pinyin: String? = null,
    val pinyinFirst: String? = null,
    val first: String? = null,
)