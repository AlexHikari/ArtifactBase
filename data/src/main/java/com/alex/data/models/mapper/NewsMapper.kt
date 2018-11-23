package com.alex.data.models.mapper

import com.alex.data.models.RawArticleOverview
import com.alex.data.models.RawNewsOverview
import com.alex.domain.models.ArticleOverview
import com.alex.domain.models.NewsOverview

/**
 * Maps from Raw response to Domain news model
 * @receiver RawNewsOverview
 * @return NewsOverview
 */
fun RawNewsOverview.toNewsOverview(): NewsOverview = NewsOverview(
        title = this.title,
        resourceURL = this.resourceURL,
        resourceIMG = this.resourceIMG
)


/**
 * Maps from Domain news model to RawData
 * @receiver NewsOverview
 * @return RawNewsOverview
 */
fun NewsOverview.toRawNewsOverview(): RawNewsOverview = RawNewsOverview(
        title = this.title,
        resourceURL = this.resourceURL,
        resourceIMG = this.resourceIMG
)


fun ArticleOverview.toRawArticleOverview(): RawArticleOverview = RawArticleOverview(
        post_title = this.post_title,
        post_text = this.post_text,
        post_image = this.post_image,
        post_date = this.post_date,
        post_url = this.post_url,
        selected = this.selected
)

fun RawArticleOverview.toArticleOverview(): ArticleOverview = ArticleOverview(
        post_title = this.post_title,
        post_text = this.post_text,
        post_image = this.post_image,
        post_date = this.post_date,
        post_url = this.post_url,
        selected = this.selected
)