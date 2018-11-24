package com.alex.data.datasource.news

import com.alex.data.models.RawArticleOverview
import com.alex.data.models.RawNewsOverview
import com.alex.data.models.mapper.toArticleOverview
import com.alex.data.models.mapper.toNewsOverview
import com.alex.data.models.mapper.toRawArticleOverview
import com.alex.data.models.mapper.toRawNewsOverview
import com.alex.domain.models.ArticleOverview
import com.alex.domain.models.NewsOverview
import com.alex.domain.repository.NewsLocalSource
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

/**
 * Creates the realm instance with all the methods needed
 * @property config (io.realm.RealmConfiguration..io.realm.RealmConfiguration?)
 * @property realm Realm
 */
class RealmSource : NewsLocalSource {


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

    override fun retrieveArticle(url: String): Single<ArticleOverview> {
        return Single.just(Realm.getInstance(config).where(RawArticleOverview::class.java).contains("post_url", url).findFirst()?.let { it.toArticleOverview() })
    }

    override fun setArticle(article: ArticleOverview) {
        Realm.getInstance(config).executeTransaction {
            it.insertOrUpdate(article.toRawArticleOverview())
        }
    }

    override fun isArticleEmpty(url: String): Boolean {
        return Realm.getInstance(config).where(RawArticleOverview::class.java).contains("post_url", url).findAll().isEmpty()
    }
}
