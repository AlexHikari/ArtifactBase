package com.alex.data.models.mapper

import com.alex.data.models.EndPointDAO
import com.alex.domain.models.EndPoint

fun EndPointDAO.toEndPoint(): EndPoint = EndPoint(
        cdnRoot = this.cdnRoot,
        url = this.url,
        expireTime = this.expireTime
)