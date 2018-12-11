package com.alex.phom.presenter

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView

class HeroDetailsPresenter(view: HeroDetailsPresenter.View, errorHandler: ErrorHandler) : Presenter<HeroDetailsPresenter.View>(view = view, errorHandler = errorHandler) {

    private var heroBundleCards = mutableListOf<CardView>()

    override fun initialize() {
        view.showProgress()
        view.getReferences()?.let {
            heroBundleCards.addAll(it)
        }
        view.hideProgress()
        view.showCardDetails(heroBundleCards)
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {

        fun getReferences(): List<CardView>?
        fun showCardDetails(heroBundleCards: List<CardView>)
    }
}