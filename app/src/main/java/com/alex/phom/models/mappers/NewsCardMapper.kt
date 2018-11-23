package com.alex.phom.models.mappers

import com.alex.domain.models.ArticleOverview
import com.alex.domain.models.NewsOverview
import com.alex.phom.models.Article
import com.alex.phom.models.NewsCard

fun NewsOverview.toNewsCard(): NewsCard = NewsCard(
        title = this.title,
        resourceIMG = this.resourceIMG,
        resourceURL = this.resourceURL
)

fun ArticleOverview.toArticle(): Article = Article(
        post_title = this.post_title,
        post_text = this.post_text,
        post_image = this.post_image,
        post_date = this.post_date,
        selected = this.selected,
        post_url = this.post_url
)

fun NewsCard.toArticle(selected: Boolean): Article = Article(
        post_url = this.resourceURL,
        post_date = "",
        post_image = this.resourceIMG,
        post_text = "",
        post_title = "",
        selected = selected
)

