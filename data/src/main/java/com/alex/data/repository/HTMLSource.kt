package com.alex.data.repository

import com.alex.data.entities.NewsCardEntity
import com.alex.data.entities.mapper.toNewsCard
import com.alex.domain.models.NewsCard
import com.alex.domain.repository.Source
import io.reactivex.Single
import org.jsoup.Jsoup

private const val PLAYARTIFACT_URL: String = "https://playartifact.com/"
private const val BLOG_LIST_HTML: String = "div.blog_post"
private const val SINGLE_NEWS_CONTAINER: String = "div.single_blog_container"
private const val BLOGPOST_IMAGE: String = "div.blog_image_container"
private const val BLOGPOST_IMAGE_FOOTER: String = "div.blog_post_footer"
private const val BLOGPOST_IMAGE_FOOTER_DARKEN: String = "div.blog_post_footer_darken"
private const val BLOGPOST_TITLE: String = "blog_post_title"

class HMTLSource : Source {

    override fun getAllNews(): Single<MutableList<NewsCard>> {
        val returnedResponse: MutableList<NewsCard> = mutableListOf()
        var singleNews: NewsCardEntity
        Jsoup.connect(PLAYARTIFACT_URL).get().run {
            select(BLOG_LIST_HTML).forEachIndexed { _, element ->
                singleNews = NewsCardEntity(
                        title = element.select("a")
                                .select(SINGLE_NEWS_CONTAINER)
                                .select(BLOGPOST_IMAGE)
                                .select(BLOGPOST_IMAGE_FOOTER)
                                .select(BLOGPOST_IMAGE_FOOTER_DARKEN)
                                .select(BLOGPOST_TITLE)
                                .html(),
                        resourceIMG = element.select("a")
                                .select(SINGLE_NEWS_CONTAINER)
                                .select(BLOGPOST_IMAGE)
                                .select("img")
                                .attr("src"),
                        resourceURL = element.select("a")
                                .attr("href")
                )
                returnedResponse.add(singleNews.toNewsCard())
            }
        }
        return Single.just(returnedResponse)
    }
}

