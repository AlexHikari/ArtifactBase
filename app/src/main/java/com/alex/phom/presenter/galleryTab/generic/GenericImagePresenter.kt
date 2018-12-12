package com.alex.phom.presenter.galleryTab.generic

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView
import com.alex.phom.presenter.Presenter

class GenericImagePresenter(view: View, errorHandler: ErrorHandler) : Presenter<GenericImagePresenter.View>(view = view, errorHandler = errorHandler) {


    private var genericCard = CardView()
    override fun initialize() {
        view.showProgress()
        view.getReferences()?.let {
            genericCard = it
        }
        view.hideProgress()
        view.showCard(genericCard)
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {
        fun getReferences(): CardView?
        fun showCard(genericCard: CardView)
    }
}