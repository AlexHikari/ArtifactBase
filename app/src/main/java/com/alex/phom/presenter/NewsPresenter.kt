package com.alex.phom.presenter

import com.alex.domain.interactor.home.GetNewsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.ArticleView
import com.alex.phom.models.NewsCardView
import com.alex.phom.models.mappers.toArticleView
import com.alex.phom.models.mappers.toNewsCardView

class NewsPresenter(private val getNewsUseCase: GetNewsUseCase, view: NewsPresenter.View, errorHandler: ErrorHandler) : Presenter<NewsPresenter.View>(view = view, errorHandler = errorHandler) {

    private val newsListView = mutableListOf<NewsCardView>()

    override fun initialize() {
        view.showProgress()
        getNewsUseCase.execute(
                onSuccess = {
                    val elements: MutableList<NewsCardView> = mutableListOf()
                    it.forEach { elem ->
                        elements.add(elem.toNewsCardView())
                        newsListView.add(elem.toNewsCardView())

                    }
                    view.hideProgress()
                    view.showNews(elements)
                },
                onError = onError { view.showError(it) },
                networkInfo = view.getNetworkInfo()
        )
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {
    }

    fun onNewClicked(selected: NewsCardView) {
        val articleViewArray: ArrayList<ArticleView> = arrayListOf()
        newsListView.forEachIndexed { index, elem ->
            if (elem == selected) {
                articleViewArray.add(index, elem.toArticleView(selected = true))
            } else {
                articleViewArray.add(index, elem.toArticleView(selected = false))
            }
        }
        view.navigateToArticle(articleViewArray)
    }


    interface View : Presenter.View {
        fun showNews(newsListView: List<NewsCardView>)
        fun navigateToArticle(articleViewList: ArrayList<ArticleView>)
        fun getNetworkInfo(): Boolean
    }
}