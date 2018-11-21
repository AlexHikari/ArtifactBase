package com.alex.data.repository

import com.alex.data.datasource.HMTLSource
import com.alex.data.datasource.RealmSource
import com.alex.domain.models.NewsOverview
import com.alex.domain.repository.INewsRepository
import io.reactivex.Single

class NewsRepository(private val remoteSource: HMTLSource, private val localSource: RealmSource) : INewsRepository {

    override fun retrieveNews(): Single<MutableList<NewsOverview>> =
            localSource.retrieveAllNews().flatMap {
                if (it.isEmpty()) {
                    remoteSource.retrieveAllNews().map { news ->
                        localSource.setNews(news)
                        return@map news
                    }
                }
                return@flatMap Single.just(it)
            }
}