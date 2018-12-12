package com.alex.data.models.mapper

import com.alex.data.models.ArticleDAO
import com.alex.data.models.NewsDAO
import com.alex.domain.models.Article
import com.alex.domain.models.News

/**
 * Maps from Raw response to Domain news model
 * @receiver NewsDAO
 * @return News
 */
fun NewsDAO.toNews(): News = News(
        title = this.title,
        resourceURL = this.resourceURL,
        resourceIMG = this.resourceIMG
)


/**
 * Maps from Domain news model to RawData
 * @receiver News
 * @return NewsDAO
 */
fun News.toNewsDAO(): NewsDAO = NewsDAO(
        title = this.title,
        resourceURL = this.resourceURL,
        resourceIMG = this.resourceIMG
)


fun Article.toArticleDAO(): ArticleDAO = ArticleDAO(
        post_title = this.post_title,
        post_text = this.post_text,
        post_image = this.post_image,
        post_date = this.post_date,
        post_url = this.post_url,
        selected = this.selected
)

fun ArticleDAO.toArticle(): Article = Article(
        post_title = this.post_title,
        post_text = this.post_text,
        post_image = this.post_image,
        post_date = this.post_date,
        post_url = this.post_url,
        selected = this.selected
)