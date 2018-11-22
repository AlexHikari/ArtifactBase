package com.alex.phom.presenter

import com.alex.domain.interactor.article.GetArticleUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.Article
import com.alex.phom.models.mappers.toArticle

class ArticlePresenter(private val getArticleUseCase: GetArticleUseCase, view: ArticlePresenter.View, errorHandler: ErrorHandler) :
        Presenter<ArticlePresenter.View>(view = view, errorHandler = errorHandler) {


    override fun initialize() {
        view.showProgress()
        getArticleUseCase.execute(
                url = view.getArticleUrl(),
                onSuccess = {
                    view.hideProgress()
                    view.showArticle(it.toArticle())
                },
                onError = onError { view.showError(it) })
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {
        fun showArticle(article: Article)
        fun getArticleUrl(): String
    }
}