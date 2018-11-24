package com.alex.data.repository

import com.alex.data.datasource.news.NewsHTMLSource
import com.alex.data.datasource.news.NewsRealmSource
import com.alex.domain.models.ArticleOverview
import com.alex.domain.models.NewsOverview
import com.alex.domain.repository.INewsRepository
import io.reactivex.Single

class NewsRepository(private val remoteSource: NewsHTMLSource, private val localSource: NewsRealmSource) : INewsRepository {


    override fun retrieveNews(networkInfo: Boolean): Single<List<NewsOverview>> {

        if (networkInfo) {
            return remoteSource.retrieveAllNews().map { news ->
                localSource.setNews(news = news)
                return@map news
            }
        } else {
            return localSource.retrieveAllNews()
        }
    }

    override fun retrieveArticle(url: String): Single<ArticleOverview> {
        if (localSource.isArticleEmpty(url)) {
            return remoteSource.retrieveArticleByUrl(url = url).map {
                localSource.setArticle(it)
                return@map it
            }
        } else {
            return localSource.retrieveArticle(url = url)
        }

    }

}