package com.alex.data.entities.mapper

import com.alex.data.entities.NewsCardEntity
import com.alex.domain.models.NewsCard


fun NewsCardEntity.toNewsCard(): NewsCard {

    return NewsCard(
            title = this.title,
            resourceURL = this.resourceURL,
            resourceIMG = this.resourceIMG
    )

}

fun NewsCard.toNewsCardEntity(): NewsCardEntity {

    return NewsCardEntity(
            title = this.title,
            resourceURL = this.resourceURL,
            resourceIMG = this.resourceIMG
    )
}