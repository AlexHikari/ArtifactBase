package com.alex.data.datasource

import com.alex.data.entities.mapper.toNewsOverview
import com.alex.data.models.RawNewsOverview
import com.alex.domain.models.NewsOverview
import com.alex.domain.repository.RemoteSource
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
class HMTLSource : RemoteSource {

    /**
     * Fetch all news from the homepage
     * @return MutableList<NewsOverview>
     */
    override fun retrieveAllNews(): Single<MutableList<NewsOverview>> {

        val returnedResponse: MutableList<NewsOverview> = mutableListOf()
        var singleNews: RawNewsOverview
        Jsoup.connect(PLAYARTIFACT_URL).get().run {
            select(BLOG_LIST_HTML).forEachIndexed { _, element ->
                singleNews = RawNewsOverview(
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
                returnedResponse.add(singleNews.toNewsOverview())
            }
        }
        return Single.just(returnedResponse)
    }
}

