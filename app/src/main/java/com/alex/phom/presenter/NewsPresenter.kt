package com.alex.phom.presenter

import com.alex.domain.interactor.home.GetNewsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.Article
import com.alex.phom.models.NewsCard
import com.alex.phom.models.mappers.toArticle
import com.alex.phom.models.mappers.toNewsCard

class NewsPresenter(private val getNewsUseCase: GetNewsUseCase, view: NewsPresenter.View, errorHandler: ErrorHandler) : Presenter<NewsPresenter.View>(view = view, errorHandler = errorHandler) {

    private val newsList: MutableList<NewsCard> = mutableListOf()

    override fun initialize() {
        view.showProgress()
        getNewsUseCase.execute(
                onSuccess = {
                    val elements: MutableList<NewsCard> = mutableListOf()
                    it.forEach { elem ->
                        elements.add(elem.toNewsCard())
                        newsList.add(elem.toNewsCard())

                    }
                    view.hideProgress()
                    view.showNews(elements)
                },
                onError = onError { view.showError(it) }
        )
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {
    }

    fun onNewClicked(selected: NewsCard) {
        val articleArray: ArrayList<Article> = arrayListOf()
        newsList.forEachIndexed { index, elem ->
            if (elem == selected) {
                articleArray.add(index, elem.toArticle(selected = true))
            } else {
                articleArray.add(index, elem.toArticle(selected = false))
            }
        }
        view.navigateToArticle(articleArray)
    }


    interface View : Presenter.View {
        fun showNews(newsList: List<NewsCard>)
        fun navigateToArticle(articleList: ArrayList<Article>)
    }
}