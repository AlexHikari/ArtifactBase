package com.alex.data.repository

import com.alex.domain.models.NewsCard
import io.reactivex.Single

class NewsRepository(private val htmlSource: HMTLSource, private val realmSource: RealmSource) {

    fun getAllNews(): Single<MutableList<NewsCard>> {
        val response: MutableList<NewsCard>
        if (realmSource.isNewsEmpty()) {
            response = htmlSource.getAllNews()
            realmSource.setAllNews(response)
        } else {
            response = realmSource.getAllNews()
        }
        
        return Single.just(response)
    }
}