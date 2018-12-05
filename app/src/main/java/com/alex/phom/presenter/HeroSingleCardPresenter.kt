package com.alex.phom.presenter

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView

class HeroSingleCardPresenter(view: HeroSingleCardPresenter.View, errorHandler: ErrorHandler) : Presenter<HeroSingleCardPresenter.View>(view = view, errorHandler = errorHandler) {

    override fun initialize() {
        view.showProgress()
        val card = view.getCard()
        view.hideProgress()
        view.showCard(card)
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {
        fun showCard(card: CardView)
        fun getCard(): com.alex.phom.models.CardView
    }

}