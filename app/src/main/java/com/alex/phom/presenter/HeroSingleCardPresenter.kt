package com.alex.phom.presenter

import com.alex.phom.error.ErrorHandler

class HeroSingleCardPresenter(view: HeroSingleCardPresenter.View, errorHandler: ErrorHandler) : Presenter<HeroSingleCardPresenter.View>(view = view, errorHandler = errorHandler) {
    override fun initialize() {
        view.showProgress()
        view.showCard()
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {
        fun showCard()
    }

}