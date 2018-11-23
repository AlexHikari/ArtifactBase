package com.alex.phom.presenter

import com.alex.domain.interactor.article.GetArticleUseCase
import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.Article
import com.alex.phom.models.mappers.toArticle

class ArticlePresenter(private val getArticleUseCase: GetArticleUseCase, view: ArticlePresenter.View, errorHandler: ErrorHandler) :
        Presenter<ArticlePresenter.View>(view = view, errorHandler = errorHandler) {

    private var articleList = arrayListOf<Article>()
    override fun initialize() {
        view.showProgress()
        articleList = view.getArticleList()
        getArticleUseCase.execute(
                url = view.getArticleUrl(articleList),
                onSuccess = {
                    view.hideProgress()
                    view.showArticle(article = it.toArticle(), isLast = false, isFist = false)
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
        fun showArticle(article: Article, isLast: Boolean, isFist: Boolean)
        fun getArticleList(): ArrayList<Article>
        fun getArticleUrl(articleList: ArrayList<Article>): String
    }
}