package com.alex.domain.repository

import com.alex.domain.models.ArticleOverview
import com.alex.domain.models.NewsOverview
import io.reactivex.Single

/**
 * Interface to be implemented by remoteSource
 */
interface RemoteSource {

    /**
     * Get all the news from the playArtifact site
     * @return MutableList<NewsCard> returns single object
     */
    fun retrieveAllNews(): Single<List<NewsOverview>>

    fun retrieveArticleByUrl(url: String): Single<ArticleOverview>
}

/**
 * Interface to be implemented by RealmSource
 */
interface LocalSource {

    /**
     * Get all the news from the playArtifact site
     * @return MutableList<NewsCard> returns single object
     */
    fun retrieveAllNews(): Single<List<NewsOverview>>

    fun isNewsEmpty(): Boolean

    fun setNews(news: List<NewsOverview>)

    fun retrieveArticle(url: String): Single<ArticleOverview>

    fun setArticle(article: ArticleOverview)

    fun isArticleEmpty(url: String): Boolean
}

/**
 * Interface to be implemented by the GetNewsUseCase
 */
interface INewsRepository {

    /**
     * Get all the news from The repositorySource
     * @return Single<MutableList<NewsOverview>>
     */
    fun retrieveNews(networkInfo: Boolean): Single<List<NewsOverview>>

    fun retrieveArticle(url: String): Single<ArticleOverview>
}

