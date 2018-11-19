package com.alex.domain.repository

import com.alex.domain.models.NewsOverview
import io.reactivex.Single

/**
 * Interface to be implemented by HTMLSource and RealmSource
 */
interface INewsSource {

    /**
     * Get all the news from the playArtifact site
     * @return MutableList<NewsCard> returns single object
     */
    fun getAllNews(): MutableList<NewsOverview>
}

/**
 * Interface to be implemented by the GetNewsUseCase
 */
interface INewsRepository {

    /**
     * Get all the news from The repositorySource
     * @return Single<MutableList<NewsOverview>>
     */
    fun getNews(): Single<MutableList<NewsOverview>>
}

