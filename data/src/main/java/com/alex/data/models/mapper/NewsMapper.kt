package com.alex.data.entities.mapper

import com.alex.data.models.RawNewsOverview
import com.alex.domain.models.NewsOverview

/**
 * Maps from Raw response to Domain news model
 * @receiver RawNewsOverview
 * @return NewsOverview
 */
fun RawNewsOverview.toNewsOverview(): NewsOverview {

    return NewsOverview(
            title = this.title,
            resourceURL = this.resourceURL,
            resourceIMG = this.resourceIMG
    )

}

/**
 * Maps from Domain news model to RawData
 * @receiver NewsOverview
 * @return RawNewsOverview
 */
fun NewsOverview.toRawNewsOverview(): RawNewsOverview {

    return RawNewsOverview(
            title = this.title,
            resourceURL = this.resourceURL,
            resourceIMG = this.resourceIMG
    )
}