package com.alex.data.datasource

import com.alex.data.models.RawNewsOverview
import com.alex.data.models.mapper.toNewsOverview
import com.alex.data.models.mapper.toRawNewsOverview
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
     * Retrieves all news from DB
     * @return MutableList<NewsOverview>
     */
    override fun retrieveAllNews(): Single<List<NewsOverview>> {
        val results: RealmResults<RawNewsOverview> = Realm.getInstance(config).where(RawNewsOverview::class.java).findAll()
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
        return Realm.getInstance(config).where(RawNewsOverview::class.java).findAll().isEmpty()
    }

    /**
     * Stores response( each news) onto the DB
     * @param news MutableList<NewsOverview>
     */
    override fun setNews(news: List<NewsOverview>) {
        news.forEach { newsItem ->
            Realm.getInstance(config).executeTransaction {
                it.insertOrUpdate(newsItem.toRawNewsOverview())
            }
        }
    }
}
