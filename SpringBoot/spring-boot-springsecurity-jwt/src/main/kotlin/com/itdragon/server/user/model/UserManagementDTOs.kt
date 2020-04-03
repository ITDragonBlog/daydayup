package com.itdragon.server.user.model

data class UserCreateInfo(
        var id: String = "",
        var username: String = "",
        var password: String = "",
        var email: String? = null,
        var nickName: String = ""
)

data class UserUpdateInfo(
        var id: String = "",
        var email: String? = null,
        var nickName: String? = null,
        var contactTel: String? = null
)
