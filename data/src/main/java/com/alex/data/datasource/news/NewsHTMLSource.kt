package com.alex.data.datasource.news

import com.alex.data.models.ArticleDAO
import com.alex.data.models.NewsDAO
import com.alex.data.models.mapper.toArticle
import com.alex.data.models.mapper.toNews
import com.alex.domain.models.Article
import com.alex.domain.models.News
import com.alex.domain.repository.NewsRemoteSource
import io.reactivex.Single
import org.jsoup.Jsoup


/**
 * All constants needed to fetch the news list from artifact site
 */
private const val PLAYARTIFACT_URL: String = "https://playartifact.com/"
private const val BLOG_LIST_HTML: String = "#blog_posts"
private const val SINGLE_NEWS_CONTAINER: String = "div.single_blog_container"
private const val BLOGPOST_IMAGE: String = "#blog_image_container"
private const val BLOGPOST_IMAGE_FOOTER: String = "div.blog_post_footer"
private const val BLOGPOST_IMAGE_FOOTER_DARKEN: String = "div.blog_post_footer_darken"
private const val BLOGPOST_TITLE: String = ".blog_post_title"

/**
 * Retrieves all things related to HTML
 */
class NewsHTMLSource : NewsRemoteSource {

    /**
     * Fetch all news from the homepage
     * @return MutableList<News>
     */
    override fun retrieveAllNews(): Single<List<News>> {
        return Single.create { emitter ->
            var hasNext = true
            var index = 1
            var url = PLAYARTIFACT_URL
            val returnedResponse = mutableListOf<News>()
            while (hasNext) {
                Jsoup.connect(url).get().run {
                    select(BLOG_LIST_HTML).select("a").forEachIndexed { _, element ->
                        if (element.hasClass("next")) {
                            hasNext = true
                            index++
                            url = PLAYARTIFACT_URL + "archive/$index"
                        } else {
                            if (!element.hasClass("previous")) {
                                val singleNews = NewsDAO(
                                        title = element.select(SINGLE_NEWS_CONTAINER)
                                                .select(BLOGPOST_IMAGE)
                                                .select(BLOGPOST_IMAGE_FOOTER)
                                                .select(BLOGPOST_IMAGE_FOOTER_DARKEN)
                                                .select(BLOGPOST_TITLE)
                                                .html(),
                                        resourceIMG = element.select(SINGLE_NEWS_CONTAINER)
                                                .select(BLOGPOST_IMAGE)
                                                .select("img")
                                                .attr("src"),
                                        resourceURL = element.attr("href")
                                )
                                returnedResponse.add(singleNews.toNews())
                                hasNext = false
                            }
                        }
                    }
                }
            }
            emitter.onSuccess(returnedResponse)
        }
    }

    override fun retrieveArticleByUrl(url: String): Single<Article> {
        return Single.create { emitter ->
            Jsoup.connect(url).get().run {
                select("#news_post_container").forEachIndexed { _, element ->

                    val article = ArticleDAO(
                            post_title = element.select(".blog_post_title").html(),
                            post_date = element.select(".blog_post_date").html(),
                            post_image = element.select(".news_blog_image").attr("src"),
                            post_text = element.select("#news_blog_post_text").html(),
                            selected = true,
                            post_url = url
                    )
                    emitter.onSuccess(article.toArticle())
                }
            }
        }
    }
}

