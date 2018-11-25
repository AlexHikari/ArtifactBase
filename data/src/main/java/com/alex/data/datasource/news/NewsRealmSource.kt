package com.alex.data.datasource.news

import com.alex.data.models.ArticleDAO
import com.alex.data.models.NewsDAO
import com.alex.data.models.mapper.toArticle
import com.alex.data.models.mapper.toArticleDAO
import com.alex.data.models.mapper.toNews
import com.alex.data.models.mapper.toNewsDAO
import com.alex.domain.models.Article
import com.alex.domain.models.News
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
class NewsRealmSource : NewsLocalSource {


    /**
     * Actual configuration of realm
     */
    private var config = RealmConfiguration.Builder().build()

    /**
     * Retrieves all news from DB
     * @return MutableList<News>
     */
    override fun retrieveAllNews(): Single<List<News>> {
        val results: RealmResults<NewsDAO> = Realm.getInstance(config).where(NewsDAO::class.java).findAll()
        val returnedResponse: MutableList<News> = mutableListOf()
        results.map {
            returnedResponse.add(it.toNews())
        }
        return Single.just(returnedResponse)
    }

    /**
     * Any News in database?
     * @return Boolean
     */
    override fun isNewsEmpty(): Boolean {
        return Realm.getInstance(config).where(NewsDAO::class.java).findAll().isEmpty()
    }

    /**
     * Stores response( each news) onto the DB
     * @param news MutableList<News>
     */
    override fun setNews(news: List<News>) {
        news.forEach { newsItem ->
            Realm.getInstance(config).executeTransaction {
                it.insertOrUpdate(newsItem.toNewsDAO())
            }
        }
    }

    override fun retrieveArticle(url: String): Single<Article> {
        return Single.just(Realm.getInstance(config).where(ArticleDAO::class.java).contains("post_url", url).findFirst()?.let { it.toArticle() })
    }

    override fun setArticle(article: Article) {
        Realm.getInstance(config).executeTransaction {
            it.insertOrUpdate(article.toArticleDAO())
        }
    }

    override fun isArticleEmpty(url: String): Boolean {
        return Realm.getInstance(config).where(ArticleDAO::class.java).contains("post_url", url).findAll().isEmpty()
    }
}
