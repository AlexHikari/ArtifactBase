package com.alex.phom.presenter.newsTab

import com.alex.domain.interactor.article.GetArticleUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.ArticleView
import com.alex.phom.models.mappers.toArticleView
import com.alex.phom.presenter.Presenter

class ArticlePresenter(private val getArticleUseCase: GetArticleUseCase, view: View, errorHandler: ErrorHandler) :
        Presenter<ArticlePresenter.View>(view = view, errorHandler = errorHandler) {

    private var articleList = arrayListOf<ArticleView>()
    override fun initialize() {
        view.showProgress()
        articleList = view.getArticleList()
        getArticleUseCase.execute(
                url = view.getArticleUrl(articleList),
                onSuccess = {
                    view.hideProgress()
                    val article = it.toArticleView()
                    if (article.post_image.isEmpty()) {
                        articleList.forEach { elem ->
                            if (article.post_url == elem.post_url) {
                                article.post_image = elem.post_image
                            }
                        }
                    }
                    view.showArticle(articleView = article, isLast = false, isFist = false)
                    view.showBack()
                },
                onError = onError { view.showError(it) })
    }

    fun onBackButtonClicked() {
        view.finishActivity()
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {
        getArticleUseCase.clear()
    }


    interface View : Presenter.View {
        fun showArticle(articleView: ArticleView, isLast: Boolean, isFist: Boolean)
        fun getArticleList(): ArrayList<ArticleView>
        fun getArticleUrl(articleViewList: ArrayList<ArticleView>): String
        fun finishActivity()
        fun showBack()
    }
}