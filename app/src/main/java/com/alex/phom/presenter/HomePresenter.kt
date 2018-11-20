package com.alex.phom.presenter

import com.alex.domain.constants.Constants.Companion.DEFAULT_INT
import com.alex.domain.interactor.home.GetNewsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.NewsCard
import com.alex.phom.models.mappers.toNewsCard

class HomePresenter(private val getNewsUseCase: GetNewsUseCase, view: HomePresenter.View, errorHandler: ErrorHandler) :
        Presenter<HomePresenter.View>(view = view, errorHandler = errorHandler) {

    var count = DEFAULT_INT

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

    fun onClickClicked() {
        view.hideProgress()
        count++
        view.showText("Count $count")
    }

    interface View : Presenter.View {
        fun showText(text: String)
        fun showNews(newsList: List<NewsCard>)
    }
}