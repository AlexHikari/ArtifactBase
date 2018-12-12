package com.alex.phom.presenter.galleryTab.generic

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView
import com.alex.phom.presenter.Presenter

class GenericDetailsPresenter(view: View, errorHandler: ErrorHandler) : Presenter<GenericDetailsPresenter.View>(view = view, errorHandler = errorHandler) {

    private var genericBundleCards = mutableListOf<CardView>()

    override fun initialize() {
        view.showProgress()
        view.getReferences()?.let {
            genericBundleCards.addAll(it)
        }
        view.hideProgress()
        view.showCardDetails(genericBundleCards)
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {

        fun getReferences(): List<CardView>?
        fun showCardDetails(genericBundleCards: List<CardView>)
    }
}