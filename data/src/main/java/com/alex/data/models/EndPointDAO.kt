package com.alex.data.models

import com.squareup.moshi.Json


data class EndPointDAO(
        @Json(name = "cdn_root")
        var cdnRoot: String,
        @Json(name = "expire_time")
        var expireTime: Int,
        @Json(name = "url")
        var url: String
)