package com.alex.data.repository

import com.alex.data.entities.NewsCardEntity
import com.alex.data.entities.mapper.toNewsCard
import com.alex.domain.models.NewsCard
import com.alex.domain.repository.Source
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults


class RealmSource : Source {

    private var config = RealmConfiguration.Builder().build()

    private val realm: Realm = Realm.getInstance(config)

    private var newsEmpty = false

    override fun getAllNews(): Single<MutableList<NewsCard>> {
        val results: RealmResults<NewsCardEntity> = realm.where(NewsCardEntity::class.java).findAll()
        val returnedResponse: MutableList<NewsCard> = mutableListOf()
        if (results.size == 0) {
            newsEmpty = true
            return Single.just(returnedResponse)
        }
        results.map {
            returnedResponse.add(it.toNewsCard())
        }
        return Single.just(returnedResponse)
    }

    fun isNewsEmpty(): Boolean {
        return newsEmpty
    }

    fun setAllNews(response: Single<MutableList<NewsCard>>) {

    }
}
