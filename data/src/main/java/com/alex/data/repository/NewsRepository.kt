package com.alex.data.repository

import com.alex.data.datasource.HMTLSource
import com.alex.data.datasource.RealmSource
import com.alex.domain.models.NewsOverview
import com.alex.domain.repository.INewsRepository
import io.reactivex.Single

class NewsRepository(private val htmlSource: HMTLSource, private val realmSource: RealmSource) : INewsRepository {

    override fun getNews(): Single<MutableList<NewsOverview>> {
        val response: MutableList<NewsOverview>
        if (realmSource.isNewsEmpty()) {
            response = htmlSource.getAllNews()
            realmSource.setAllNews(response)
        } else {
            response = realmSource.getAllNews()
        }

        return Single.just(response)
    }
}