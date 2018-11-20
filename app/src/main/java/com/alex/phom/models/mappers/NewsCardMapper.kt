package com.alex.phom.models.mappers

import com.alex.domain.models.NewsOverview
import com.alex.phom.models.NewsCard

fun NewsOverview.toNewsCard(): NewsCard {

    return NewsCard(
            title = this.title,
            resourceIMG = this.resourceIMG,
            resourceURL = this.resourceURL
    )

}

