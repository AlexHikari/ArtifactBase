package com.alex.phom.presenter

import com.alex.phom.error.ErrorHandler

class ArticlePresenter(view: ArticlePresenter.View, errorHandler: ErrorHandler) :
        Presenter<ArticlePresenter.View>(view = view, errorHandler = errorHandler) {


    override fun initialize() {

    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {
        fun showArticle()
    }
}