package com.alex.data.datasource

import com.alex.data.entities.mapper.toNewsOverview
import com.alex.data.entities.mapper.toRawNewsOverview
import com.alex.data.models.RawNewsOverview
import com.alex.domain.models.NewsOverview
import com.alex.domain.repository.LocalSource
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

/**
 * Creates the realm instance with all the methods needed
 * @property config (io.realm.RealmConfiguration..io.realm.RealmConfiguration?)
 * @property realm Realm
 */
class RealmSource : LocalSource {

    /**
     * Actual configuration of realm
     */
    private var config = RealmConfiguration.Builder().build()

    /**
     * Realm instance
     */
    private val realm: Realm = Realm.getInstance(config)

    /**
     * Retrieves all news from DB
     * @return MutableList<NewsOverview>
     */
    override fun retrieveAllNews(): Single<MutableList<NewsOverview>> {
        val results: RealmResults<RawNewsOverview> = realm.where(RawNewsOverview::class.java).findAll()
        val returnedResponse: MutableList<NewsOverview> = mutableListOf()
        results.map {
            returnedResponse.add(it.toNewsOverview())
        }
        return Single.just(returnedResponse)
    }

    /**
     * Any News in database?
     * @return Boolean
     */
    override fun isNewsEmpty(): Boolean {
        return realm.where(RawNewsOverview::class.java).findAll().isEmpty()
    }

    /**
     * Stores response( each news) onto the DB
     * @param news MutableList<NewsOverview>
     */
    override fun setNews(news: MutableList<NewsOverview>) {
        news.forEach { newsItem ->
            realm.beginTransaction()
            realm.insertOrUpdate(newsItem.toRawNewsOverview())
            realm.commitTransaction()
        }
    }
}
