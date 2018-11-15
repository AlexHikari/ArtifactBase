package com.alex.phom.presenter

import com.alex.data.utils.encodeDeck
import com.alex.domain.constants.Constants.Companion.DEFAULT_INT
import com.alex.phom.error.ErrorHandler

class HomePresenter(view: HomePresenter.View, errorHandler: ErrorHandler) :
        Presenter<HomePresenter.View>(view = view, errorHandler = errorHandler) {

    var count = DEFAULT_INT

    override fun initialize() {
        view.showProgress()
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
    }
}