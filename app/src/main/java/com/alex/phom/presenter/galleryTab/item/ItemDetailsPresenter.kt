package com.alex.phom.presenter.galleryTab.item

import com.alex.phom.error.ErrorHandler
import com.alex.phom.models.CardView
import com.alex.phom.presenter.Presenter

class ItemDetailsPresenter(view: View, errorHandler: ErrorHandler) : Presenter<ItemDetailsPresenter.View>(view = view, errorHandler = errorHandler) {

    private var itemCard = CardView()

    override fun initialize() {
        view.showProgress()
        view.getReferences()?.let {
            itemCard = it
        }
        view.hideProgress()
        view.showCardDetails(itemCard)
    }

    override fun resume() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }


    interface View : Presenter.View {

        fun getReferences(): CardView?
        fun showCardDetails(card: CardView)
    }
}