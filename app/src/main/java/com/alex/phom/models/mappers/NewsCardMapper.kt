package com.alex.phom.models.mappers

import com.alex.domain.models.Article
import com.alex.domain.models.News
import com.alex.phom.models.ArticleView
import com.alex.phom.models.NewsCardView

fun News.toNewsCardView(): NewsCardView = NewsCardView(
        title = this.title,
        resourceIMG = this.resourceIMG,
        resourceURL = this.resourceURL
)

fun Article.toArticleView(): ArticleView = ArticleView(
        post_title = this.post_title,
        post_text = this.post_text,
        post_image = this.post_image,
        post_date = this.post_date,
        selected = this.selected,
        post_url = this.post_url
)

fun NewsCardView.toArticleView(selected: Boolean): ArticleView = ArticleView(
        post_url = this.resourceURL,
        post_date = "",
        post_image = this.resourceIMG,
        post_text = "",
        post_title = "",
        selected = selected
)

