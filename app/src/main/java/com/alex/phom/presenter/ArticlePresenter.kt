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
                    val article = it.toArticle()
                    if (article.post_image.isEmpty()) {
                        articleList.forEach { elem ->
                            if (article.post_url == elem.post_url) {
                                article.post_image = elem.post_image
                            }
                        }
                    }
                    view.showArticle(article = article, isLast = false, isFist = false)
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

    }


    interface View : Presenter.View {
        fun showArticle(article: Article, isLast: Boolean, isFist: Boolean)
        fun getArticleList(): ArrayList<Article>
        fun getArticleUrl(articleList: ArrayList<Article>): String
        fun finishActivity()
    }
}