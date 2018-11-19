package com.alex.domain.repository

import com.alex.domain.models.NewsCard
import io.reactivex.Single

/**
 * Interface to implement in data module with either jsoup or realm
 */
interface Source {

    /**
     * Get all the news from the playArtifact site
     * @return SingleInteractor<MutableList<NewsCard>> returns single object
     */
    fun getAllNews(): Single<MutableList<NewsCard>>
}

