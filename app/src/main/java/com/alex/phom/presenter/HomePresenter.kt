package com.alex.phom.presenter

import com.alex.domain.interactor.home.GetNewsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.NewsCard
import com.alex.phom.models.mappers.toNewsCard

class HomePresenter(private val getNewsUseCase: GetNewsUseCase, view: HomePresenter.View, errorHandler: ErrorHandler) :
        Presenter<HomePresenter.View>(view = view, errorHandler = errorHandler) {

    override fun initialize() {

        view.showProgress()
        getNewsUseCase.execute(
                onSuccess = {
                    val elements: MutableList<NewsCard> = mutableListOf()
                    it.forEach { elem ->
                        elements.add(elem.toNewsCard())
                    }
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

    interface View : Presenter.View {
        fun showNews(newsList: List<NewsCard>)
    }
}