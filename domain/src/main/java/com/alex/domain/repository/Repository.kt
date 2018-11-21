package com.alex.domain.repository

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
    fun retrieveAllNews(): Single<MutableList<NewsOverview>>
}

/**
 * Interface to be implemented by RealmSource
 */
interface LocalSource {

    /**
     * Get all the news from the playArtifact site
     * @return MutableList<NewsCard> returns single object
     */
    fun retrieveAllNews(): Single<MutableList<NewsOverview>>

    fun isNewsEmpty(): Boolean

    fun setNews(response: MutableList<NewsOverview>)
}

/**
 * Interface to be implemented by the GetNewsUseCase
 */
interface INewsRepository {

    /**
     * Get all the news from The repositorySource
     * @return Single<MutableList<NewsOverview>>
     */
    fun retrieveNews(): Single<MutableList<NewsOverview>>
}

