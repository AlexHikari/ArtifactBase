package com.alex.data.repository

import com.alex.data.entities.NewsCardEntity
import com.alex.data.entities.mapper.toNewsCard
import com.alex.data.entities.mapper.toNewsCardEntity
import com.alex.domain.models.NewsCard
import com.alex.domain.repository.Source
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults


class RealmSource : Source {

    private var config = RealmConfiguration.Builder().build()

    private val realm: Realm = Realm.getInstance(config)

    override fun getAllNews(): MutableList<NewsCard> {
        val results: RealmResults<NewsCardEntity> = realm.where(NewsCardEntity::class.java).findAll()
        val returnedResponse: MutableList<NewsCard> = mutableListOf()
        results.map {
            returnedResponse.add(it.toNewsCard())
        }
        return returnedResponse
    }

    fun isNewsEmpty(): Boolean {
        return realm.where(NewsCardEntity::class.java).findAll().isEmpty()
    }

    fun setAllNews(response: MutableList<NewsCard>) {
        response.forEach { newsItem ->
            realm.beginTransaction()
            realm.insertOrUpdate(newsItem.toNewsCardEntity())
            realm.commitTransaction()
        }
    }
}
