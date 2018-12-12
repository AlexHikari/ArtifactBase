package com.alex.domain.models


data class EndPoint(
        var cdnRoot: String,
        var expireTime: Int,
        var url: String
)