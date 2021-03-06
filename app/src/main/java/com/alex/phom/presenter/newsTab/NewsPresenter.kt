package com.alex.phom.presenter.newsTab

import com.alex.domain.interactor.home.GetNewsUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.ArticleView
import com.alex.phom.models.NewsCardView
import com.alex.phom.models.mappers.toArticleView
import com.alex.phom.models.mappers.toNewsCardView
import com.alex.phom.presenter.Presenter

class NewsPresenter(private val getNewsUseCase: GetNewsUseCase, view: View, errorHandler: ErrorHandler) : Presenter<NewsPresenter.View>(view = view, errorHandler = errorHandler) {

    private val newsListView = mutableListOf<NewsCardView>()

    override fun initialize() {
        view.hideIcons()
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
        getNewsUseCase.clear()
    }

    override fun destroy() {
        getNewsUseCase.clear()
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
        fun hideIcons()
    }
}