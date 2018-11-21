package com.alex.phom.presenter

import com.alex.domain.interactor.home.GetNewsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.NewsCard
import com.alex.phom.models.mappers.toNewsCard

class NewsPresenter(private val getNewsUseCase: GetNewsUseCase, view: NewsPresenter.View, errorHandler: ErrorHandler) : Presenter<NewsPresenter.View>(view = view, errorHandler = errorHandler) {


    override fun initialize() {
        view.showProgress()
        getNewsUseCase.execute(
                onSuccess = {
                    val elements: MutableList<NewsCard> = mutableListOf()
                    it.forEach { elem ->
                        elements.add(elem.toNewsCard())
                    }
                    view.showNews(elements)
                    view.hideProgress()
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

    fun onNewClicked(newsCard: NewsCard) {
        view.navigateToArticle()
    }


    interface View : Presenter.View {
        fun showNews(newsList: List<NewsCard>)
        fun navigateToArticle()
    }
}