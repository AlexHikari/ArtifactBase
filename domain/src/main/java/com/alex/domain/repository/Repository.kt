package com.alex.domain.repository

import com.alex.domain.models.*
import io.reactivex.Flowable
import io.reactivex.Single


interface CardSetRemoteSource {
    fun retrieveEndPoint(url: String): Single<EndPoint>
    fun retrieveCards(firstEndpoint: String, secondEndpoint: String): Flowable<CardSet>
}

interface CardSetLocalSource {
    fun retrieveCards()
}

/**
 * Interface to be implemented by remoteSource
 */
interface NewsRemoteSource {

    /**
     * Get all the news from the playArtifact site
     * @return MutableList<NewsCard> returns single object
     */
    fun retrieveAllNews(): Single<List<News>>

    fun retrieveArticleByUrl(url: String): Single<Article>
}

/**
 * Interface to be implemented by RealmSource
 */
interface NewsLocalSource {

    /**
     * Get all the news from the playArtifact site
     * @return MutableList<NewsCard> returns single object
     */
    fun retrieveAllNews(): Single<List<News>>

    fun isNewsEmpty(): Boolean

    fun setNews(news: List<News>)

    fun retrieveArticle(url: String): Single<Article>

    fun setArticle(article: Article)

    fun isArticleEmpty(url: String): Boolean
}

/**
 * Interface to be implemented by the GetNewsUseCase
 */
interface INewsRepository {

    /**
     * Get all the news from The repositorySource
     * @return Single<MutableList<News>>
     */
    fun retrieveNews(networkInfo: Boolean): Single<List<News>>

    fun retrieveArticle(url: String): Single<Article>
}


interface ICardSetRepository {

    fun retrieveAllCards(): Flowable<CardSet>
    fun retrieveSingleCard(cardId: Long): Single<Card>
}

